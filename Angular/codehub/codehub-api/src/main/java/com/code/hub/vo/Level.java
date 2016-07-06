package com.code.hub.vo;

/**
 * Represents level of the problems
 * 
 * @author tbhakre
 *
 */
public enum Level {

	EASY("Easy"), MEDIUM("Medium"), HARD("Hard");

	private String level;

	Level(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

}
