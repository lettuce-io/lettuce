/*
 * Copyright 2025, Redis Ltd. and Contributors
 * All rights reserved.
 *
 * Licensed under the MIT License.
 */
package io.lettuce.core.api.async;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.search.Fields;
import io.lettuce.core.search.arguments.CreateArgs;

/**
 * Asynchronous executed commands for RediSearch functionality
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Tihomir Mateev
 * @see <a href="https://redis.io/docs/latest/operate/oss_and_stack/stack-with-enterprise/search/">RediSearch</a>
 * @since 6.6
 * @generated by io.lettuce.apigenerator.CreateAsyncApi
 */
public interface RediSearchAsyncCommands<K, V> {

    /**
     * Create a new index with the given name, index options and fields.
     *
     * @param index the index name
     * @param options the index options
     * @param fields the fields
     * @return the result of the create command
     */
    RedisFuture<String> ftCreate(K index, CreateArgs<K, V> options, Fields<K> fields);

}
