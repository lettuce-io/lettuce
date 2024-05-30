/*
 * Copyright 2017-Present, Redis Ltd. and Contributors
 * All rights reserved.
 *
 * Licensed under the MIT License.
 *
 * This file contains contributions from third-party contributors
 * licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.lettuce.core.api.sync;

import java.util.List;

import io.lettuce.core.FlushMode;
import io.lettuce.core.ScriptOutputType;

/**
 * Synchronous executed commands for Scripting. {@link java.lang.String Lua scripts} are encoded by using the configured
 * {@link io.lettuce.core.ClientOptions#getScriptCharset() charset}.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mark Paluch
 * @since 4.0
 * @generated by io.lettuce.apigenerator.CreateSyncApi
 */
public interface RedisScriptingCommands<K, V> {

    /**
     * Execute a Lua script server side.
     *
     * @param script Lua 5.1 script.
     * @param type output type.
     * @param keys key names.
     * @param <T> expected return type.
     * @return script result.
     */
    <T> T eval(String script, ScriptOutputType type, K... keys);

    /**
     * Execute a Lua script server side.
     *
     * @param script Lua 5.1 script.
     * @param type output type.
     * @param keys key names.
     * @param <T> expected return type.
     * @return script result.
     * @since 6.0
     */
    <T> T eval(byte[] script, ScriptOutputType type, K... keys);

    /**
     * Execute a Lua script server side.
     *
     * @param script Lua 5.1 script.
     * @param type output type.
     * @param keys the keys.
     * @param values the values.
     * @param <T> expected return type.
     * @return script result.
     */
    <T> T eval(String script, ScriptOutputType type, K[] keys, V... values);

    /**
     * Execute a Lua script server side.
     *
     * @param script Lua 5.1 script.
     * @param type output type.
     * @param keys the keys.
     * @param values the values.
     * @param <T> expected return type.
     * @return script result.
     * @since 6.0
     */
    <T> T eval(byte[] script, ScriptOutputType type, K[] keys, V... values);

    /**
     * This is a read-only variant of the EVAL command that cannot execute commands that modify data.
     *
     * @param script Lua 5.1 script.
     * @param type output type.
     * @param keys the keys.
     * @param values the values.
     * @param <T> expected return type.
     * @return script result.
     * @since 6.4
     */
    <T> T evalReadOnly(String script, ScriptOutputType type, K[] keys, V... values);

    /**
     * This is a read-only variant of the EVAL command that cannot execute commands that modify data.
     *
     * @param script Lua 5.1 script.
     * @param type output type.
     * @param keys the keys.
     * @param values the values.
     * @param <T> expected return type.
     * @return script result.
     * @since 6.2
     */
    <T> T evalReadOnly(byte[] script, ScriptOutputType type, K[] keys, V... values);

    /**
     * Evaluates a script cached on the server side by its SHA1 digest.
     *
     * @param digest SHA1 of the script.
     * @param type output type.
     * @param keys the keys.
     * @param <T> expected return type.
     * @return script result.
     */
    <T> T evalsha(String digest, ScriptOutputType type, K... keys);

    /**
     * Execute a Lua script server side.
     *
     * @param digest SHA1 of the script.
     * @param type output type.
     * @param keys the keys.
     * @param values the values.
     * @param <T> expected return type.
     * @return script result.
     */
    <T> T evalsha(String digest, ScriptOutputType type, K[] keys, V... values);

    /**
     * This is a read-only variant of the EVALSHA command that cannot execute commands that modify data.
     *
     * @param digest SHA1 of the script.
     * @param type output type.
     * @param keys the keys.
     * @param values the values.
     * @param <T> expected return type.
     * @return script result.
     * @since 6.2
     */
    <T> T evalshaReadOnly(String digest, ScriptOutputType type, K[] keys, V... values);

    /**
     * Check existence of scripts in the script cache.
     *
     * @param digests script digests.
     * @return List&lt;Boolean&gt; array-reply The command returns an array of integers that correspond to the specified SHA1
     *         digest arguments. For every corresponding SHA1 digest of a script that actually exists in the script cache, an 1
     *         is returned, otherwise 0 is returned.
     */
    List<Boolean> scriptExists(String... digests);

    /**
     * Remove all the scripts from the script cache.
     *
     * @return String simple-string-reply.
     */
    String scriptFlush();

    /**
     * Remove all the scripts from the script cache using the specified {@link FlushMode}.
     *
     * @param flushMode the flush mode (sync/async).
     * @return String simple-string-reply.
     * @since 6.1
     */
    String scriptFlush(FlushMode flushMode);

    /**
     * Kill the script currently in execution.
     *
     * @return String simple-string-reply.
     */
    String scriptKill();

    /**
     * Load the specified Lua script into the script cache.
     *
     * @param script script content.
     * @return String bulk-string-reply This command returns the SHA1 digest of the script added into the script cache.
     * @since 6.0
     */
    String scriptLoad(String script);

    /**
     * Load the specified Lua script into the script cache.
     *
     * @param script script content.
     * @return String bulk-string-reply This command returns the SHA1 digest of the script added into the script cache.
     * @since 6.0
     */
    String scriptLoad(byte[] script);

    /**
     * Create a SHA1 digest from a Lua script.
     *
     * @param script script content.
     * @return the SHA1 value.
     * @since 6.0
     */
    String digest(String script);

    /**
     * Create a SHA1 digest from a Lua script.
     *
     * @param script script content.
     * @return the SHA1 value.
     * @since 6.0
     */
    String digest(byte[] script);

}
