package com.untec.shared.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUUIDUtils {
	public static byte[] toBytes(UUID uuid) {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
		buffer.putLong(uuid.getMostSignificantBits());
		buffer.putLong(uuid.getLeastSignificantBits());
		return buffer.array();
	}
	
	public static UUID fromBytes(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		long first = buffer.getLong();
		long second = buffer.getLong();
		return new UUID(first, second);
	}
}
