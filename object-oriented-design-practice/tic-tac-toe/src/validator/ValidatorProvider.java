package validator;

public interface ValidatorProvider {
	boolean hasNext();

	Validator next();
}
