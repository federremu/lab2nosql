package com.paquete.model;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.mongodb.core.query.SerializationUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * Java Serialization Redis serializer. Delegates to the default (Java based) {@link DefaultSerializer serializer} and
 * {@link DefaultDeserializer}. This {@link RedisSerializer serializer} can be constructed with either custom
 * {@link ClassLoader} or own {@link Converter converters}.
 *
 * @author Mark Pollack
 * @author Costin Leau
 * @author Mark Paluch
 * @author Christoph Strobl
 */
public class LdapFailAwareRedisObjectSerializer implements RedisSerializer<Object> {

    private final Converter<Object, byte[]> serializer;
    private final Converter<byte[], Object> deserializer;

    static final byte[] EMPTY_ARRAY = new byte[0];

    /**
     * Creates a new {@link LdapFailAwareRedisObjectSerializer} using the default
     * class loader.
     */
    public LdapFailAwareRedisObjectSerializer() {
        this(new SerializingConverter(), new DeserializingConverter());
    }

    /**
     * Creates a new {@link LdapFailAwareRedisObjectSerializer} using a
     * {@link ClassLoader}.
     *
     * @param classLoader the {@link ClassLoader} to use for deserialization. Can be
     *                    {@literal null}.
     * @since 1.7
     */
    public LdapFailAwareRedisObjectSerializer(@Nullable ClassLoader classLoader) {
        this(new SerializingConverter(), new DeserializingConverter(classLoader));
    }

    /**
     * Creates a new {@link LdapFailAwareRedisObjectSerializer} using a
     * {@link Converter converters} to serialize and deserialize objects.
     *
     * @param serializer   must not be {@literal null}
     * @param deserializer must not be {@literal null}
     * @since 1.7
     */
    public LdapFailAwareRedisObjectSerializer(Converter<Object, byte[]> serializer,
            Converter<byte[], Object> deserializer) {

		Assert.notNull(serializer, "Serializer must not be null!");
		Assert.notNull(deserializer, "Deserializer must not be null!");

		this.serializer = serializer;
		this.deserializer = deserializer;
	}

	public Object deserialize(@Nullable byte[] bytes) {

        if (bytes.equals(null)) {
            return null;
        }

		try {
			return deserializer.convert(bytes);
		} catch (Exception ex) {
			throw new SerializationException("Cannot deserialize", ex);
		}
	}

	@Override
	public byte[] serialize(@Nullable Object object) {
		if (object == null) {
			return EMPTY_ARRAY;
		}
		try {
			return serializer.convert(object);
		} catch (Exception ex) {
			throw new SerializationException("Cannot serialize", ex);
		}
	}
}