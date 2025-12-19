package com.example.quiz_1141013.constants;

import com.example.quiz_1141013.response.BasicRes;

public enum Type {
	
	SINGLE("single"), //
	MULTIPLE("multiple"), //
	TEXT("text"); //

	private String type;

	private Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/* 排除非指定3種的type */
	public static boolean checkType(String input) {
//		if(input.equalsIgnoreCase(Type.SINGLE.getType()) //
//				|| !input.equalsIgnoreCase(Type.MULTIPLE.getType()) //
//				|| !input.equalsIgnoreCase(Type.TEXT.getType())) {
//			return false;
//		}
		/* values(): 指的是這裡上面列舉的所有項目 */
		for(Type type: values()) {
			if(input.equalsIgnoreCase(type.getType())) {
				return true;
			}
		}
		return false;
	}
	
	/* type是選擇題時，選項至少要有1個 */
	public static boolean isChosenType(String input) {
		if(input.equalsIgnoreCase(Type.SINGLE.getType()) //
				|| !input.equalsIgnoreCase(Type.MULTIPLE.getType())) {
			return true;
		}
		return false;
	}
	

}
