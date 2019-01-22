package com.dcits.paramManage.util;

import java.util.UUID;

public class UUIDUtil {

	/**
	 * 表id uuid
	 * @return
	 */
	public static String getUUID(){
		String uuid=UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
}
