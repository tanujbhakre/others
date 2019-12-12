package dependencies;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CommandExecutorTest {

	private CommandExecutor commandExecutor;

	@Before
	public void setUp() {
		commandExecutor = new CommandExecutor();
	}

	@Test
	public void givenDependOperation_whenInsufficientArgument_thenErrorMessage() {
		List<StringBuilder> result = commandExecutor.execute(Arrays.asList("DEPEND", "A"));
		assertTrue(result.get(0).toString().equals("Invalid argument"));
	}

	@Test
	public void givenOperation_whenUnknownOperation_thenEmptyMessage() {
		List<StringBuilder> result = commandExecutor.execute(Arrays.asList("X", "A"));
		assertTrue(result.size() == 0);
	}

	@Test
	public void givenOperation_whenEmptyArgument_thenEmptyMessage() {
		List<StringBuilder> result = commandExecutor.execute(new ArrayList<String>());
		assertTrue(result.size() == 0);
	}

	@Test
	public void givenDependOperation_whenParentDoesNotDepenOnChild_thenEmptyResult() {
		String child = "A";
		String parent = "B";
		List<String> childParentDependency = getChildParentDependOperation(child, parent);
		List<StringBuilder> result = commandExecutor.execute(childParentDependency);
		assertTrue(result.size() == 0);
	}

	@Test
	public void givenDependOperation_whenParentDependsOnChild_thenErrorMessage() {
		String child = "A";
		String parent = "B";
		List<String> childParentDependency = getChildParentDependOperation(child, parent);
		commandExecutor.execute(childParentDependency);
		List<String> childParentOperation = getChildParentDependOperation(parent, child);
		List<StringBuilder> result = commandExecutor.execute(childParentOperation);
		assertTrue(result.get(0).toString().equals(child + " depends on " + parent + ", ignoring command"));
	}

	@Test
	public void givenDependOperation_whenParentDependsOnGrandChild_thenErrorMessage() {
		String parent = "A";
		String node = "B";
		String child = "C";
		List<String> childParentDependency = getChildParentDependOperation(child, node);
		commandExecutor.execute(childParentDependency);
		List<String> nodeParentDependency = getChildParentDependOperation(node, parent);
		commandExecutor.execute(nodeParentDependency);
		List<String> invalidParentChildDependencyCommand = getChildParentDependOperation(parent, child);
		List<StringBuilder> result = commandExecutor.execute(invalidParentChildDependencyCommand);
		assertTrue(result.get(0).toString().equals(child + " depends on " + parent + ", ignoring command"));
	}

	@Test
	public void givenDependOperation_whenNodeWithMultipleParentIsInstalled_thenErrorMessage() {
		String parent1 = "AA";
		String parent2 = "AB";
		String node = "B";
		String child = "C";
		List<String> nodeParentDependency = getChildParentsDependOperation(node, Arrays.asList(parent1, parent2));
		commandExecutor.execute(nodeParentDependency);
		List<String> childNodeDependency = getChildParentDependOperation(child, node);
		commandExecutor.execute(childNodeDependency);
		List<String> childParent1InvalidDependency = getChildParentDependOperation(parent1, child);
		List<StringBuilder> result = commandExecutor.execute(childParent1InvalidDependency);
		assertTrue(result.get(0).toString().equals(child + " depends on " + parent1 + ", ignoring command"));
		List<String> childParent2InvalidDependency = getChildParentDependOperation(parent2, child);
		List<StringBuilder> result2 = commandExecutor.execute(childParent2InvalidDependency);
		assertTrue(result2.get(0).toString().equals(child + " depends on " + parent2 + ", ignoring command"));
	}

	@Test
	public void givenInstallOperation_whenIndependentComponent_thenComponentIsInstalled() {
		String component = "A";
		List<String> componentInstall = getInstallOperation(component);
		List<StringBuilder> result = commandExecutor.execute(componentInstall);
		assertTrue(result.get(0).toString().equals("Installing " + component));
	}

	@Test
	public void givenInstallOperation_whenComponentIsInstalledAgain_thenComponentIsInstalledMessage() {
		String component = "A";
		List<String> componentInstall = getInstallOperation(component);
		commandExecutor.execute(componentInstall);
		List<StringBuilder> result = commandExecutor.execute(componentInstall);
		assertTrue(result.get(0).toString().equals(component + " is already installed"));
	}

	@Test
	public void givenParentChildDependency_whenChildIsInstalled_thenParentIsAlsoInstalled() {
		String parent = "A";
		String child = "B";
		List<String> childParentDependency = getChildParentDependOperation(child, parent);
		commandExecutor.execute(childParentDependency);

		List<String> childInstall = getInstallOperation(child);
		List<StringBuilder> result = commandExecutor.execute(childInstall);
		assertTrue(result.get(0).toString().equals("Installing " + parent));
		assertTrue(result.get(1).toString().equals("Installing " + child));
	}

	@Test
	public void givenGrandParentChildDependency_whenChildIsInstalled_thenGrandParentAndParentAreAlsoInstalled() {
		String parent = "A";
		String node = "B";
		String child = "C";
		List<String> nodeParentDependency = getChildParentDependOperation(node, parent);
		commandExecutor.execute(nodeParentDependency);

		List<String> childNodeDependency = getChildParentDependOperation(child, node);
		commandExecutor.execute(childNodeDependency);

		List<String> installChild = getInstallOperation(child);
		List<StringBuilder> result = commandExecutor.execute(installChild);
		assertTrue(result.get(0).toString().equals("Installing " + parent));
		assertTrue(result.get(1).toString().equals("Installing " + node));
		assertTrue(result.get(2).toString().equals("Installing " + child));
	}

	@Test
	public void givenParentWithMultipleDependency_whenChildIsInstalled_thenParentsDependencyIsAlsoInstalled() {
		String parent1 = "AA";
		String parent2 = "AB";
		String node = "B";
		String child = "C";
		List<String> nodeParentDependency = getChildParentsDependOperation(node, Arrays.asList(parent1, parent2));
		commandExecutor.execute(nodeParentDependency);

		List<String> childNodeDependency = getChildParentDependOperation(child, node);
		commandExecutor.execute(childNodeDependency);

		List<String> installArgs = getInstallOperation(child);
		List<StringBuilder> result = commandExecutor.execute(installArgs);
		assertTrue(result.get(0).toString().equals("Installing " + parent1));
		assertTrue(result.get(1).toString().equals("Installing " + parent2));
		assertTrue(result.get(2).toString().equals("Installing " + node));
		assertTrue(result.get(3).toString().equals("Installing " + child));
	}

	@Test
	public void givenNoInstallation_whenUninstalledComponentIsRemoved_thenNotInstalledErrorMessage() {
		String component = "C";
		List<String> removeComponent = getRemoveOperation(component);
		List<StringBuilder> result = commandExecutor.execute(removeComponent);

		assertTrue(result.get(0).toString().equals(component + " is not installed"));
	}

	@Test
	public void givenParentChildInstallation_whenparentIsRemoved_thenIsStillNeededMessage() {

		String child = "A";
		String parent = "B";
		List<String> parentChildDependency = getChildParentDependOperation(child, parent);
		commandExecutor.execute(parentChildDependency);

		List<String> childInstallation = getInstallOperation(child);
		commandExecutor.execute(childInstallation);

		List<String> removeChild = getRemoveOperation(parent);
		List<StringBuilder> result = commandExecutor.execute(removeChild);
		assertTrue(result.get(0).toString().equals(parent + " is still needed"));
	}

	@Test
	public void givenParentWithMultipleDependencyAndInstallation_whenChildIsRemoved_thenParentIsAlsoRemoved() {
		String parent = "A";
		String node = "B";
		String child = "C";
		List<String> nodeParentDependency = getChildParentDependOperation(node, parent);
		commandExecutor.execute(nodeParentDependency);

		List<String> childNodeDependency = getChildParentDependOperation(child, node);
		commandExecutor.execute(childNodeDependency);

		List<String> installChild = getInstallOperation(child);
		commandExecutor.execute(installChild);

		List<String> removeChild = getRemoveOperation(child);
		List<StringBuilder> result = commandExecutor.execute(removeChild);

		assertTrue(result.get(0).toString().equals("Removing " + child));
		assertTrue(result.get(1).toString().equals("Removing " + node));
		assertTrue(result.get(2).toString().equals("Removing " + parent));
	}

	@Test
	public void givenParentWithMultipleDependencyAndInstallationWithImplicitParent_whenChildIsRemoved_thenParentIsNotRemoved() {
		String parent = "A";
		String child = "C";
		List<String> parentChildDependency = getChildParentDependOperation(child, parent);
		commandExecutor.execute(parentChildDependency);

		List<String> installParent = getInstallOperation(parent);
		commandExecutor.execute(installParent);

		List<String> installChild = getInstallOperation(child);
		commandExecutor.execute(installChild);

		List<String> removeChild = getRemoveOperation(child);
		List<StringBuilder> result = commandExecutor.execute(removeChild);

		assertTrue(result.size() == 1);
		assertTrue(result.get(0).toString().equals("Removing " + child));
	}

	@Test
	public void givenParentWithMultipleDependencyAndInstallation_whenListIsInvoked_thenInstalledComponentsAreListed() {
		String parent = "A";
		String node = "B";
		String child = "C";
		List<String> nodeParentDependency = getChildParentDependOperation(node, parent);
		commandExecutor.execute(nodeParentDependency);

		List<String> childNodeDependency = getChildParentDependOperation(child, node);
		commandExecutor.execute(childNodeDependency);

		List<String> installChild = getInstallOperation(child);
		commandExecutor.execute(installChild);

		List<String> listAll = getListOperation();
		List<StringBuilder> result = commandExecutor.execute(listAll);

		assertTrue(result.get(0).toString().equals(parent));
		assertTrue(result.get(1).toString().equals(node));
		assertTrue(result.get(2).toString().equals(child));
	}

	private List<String> getChildParentDependOperation(String child, String parent) {
		return Arrays.asList("DEPEND", child, parent);
	}

	private List<String> getChildParentsDependOperation(String child, List<String> parents) {
		List<String> result = new ArrayList<>();
		result.add("DEPEND");
		result.add(child);
		for (String parent : parents) {
			result.add(parent);
		}
		return result;
	}

	private List<String> getInstallOperation(String component) {
		return Arrays.asList("INSTALL", component);
	}

	private List<String> getRemoveOperation(String component) {
		return Arrays.asList("REMOVE", component);
	}

	private List<String> getListOperation() {
		return Arrays.asList("LIST");
	}
}
