/*
 * Copyright 2025, Redis Ltd. and Contributors
 * All rights reserved.
 *
 * Licensed under the MIT License.
 */
package io.lettuce.core.api;

import io.lettuce.core.search.Field;
import io.lettuce.core.search.arguments.CreateArgs;

import java.util.List;

/**
 * ${intent} for RediSearch functionality
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Tihomir Mateev
 * @see <a href="https://redis.io/docs/latest/operate/oss_and_stack/stack-with-enterprise/search/">RediSearch</a>
 * @since 6.6
 */
public interface RediSearchCommands<K, V> {

    /**
     * Create a new index with the given name, index options, and fields.
     *
     * @param index the index name, as a key
     * @param options the index {@link CreateArgs}
     * @param fields the {@link Field}s of the index
     * @return the result of the create command
     * @since 6.6
     * @see <a href="https://redis.io/docs/latest/commands/ft.create/">FT.CREATE</a>
     */
    String ftCreate(K index, CreateArgs<K, V> options, List<Field<K>> fields);

}
