/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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

package io.lettuce.core.api.coroutines

import io.lettuce.core.*
import kotlinx.coroutines.flow.Flow

/**
 * Coroutine executed commands for Sorted Sets.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mikhael Sokolov
 * @since 6.0
 * @generated by io.lettuce.apigenerator.CreateKotlinCoroutinesApi
 */
@ExperimentalLettuceCoroutinesApi
interface RedisSortedSetSuspendableCommands<K : Any, V : Any> {

    /**
     * Removes and returns a member with the lowest scores in the sorted set stored at one of the keys.
     *
     * @param timeout the timeout in seconds.
     * @param keys the keys.
     * @return KeyValue<K, ScoredValue<V>> multi-bulk containing the name of the key, the score and the popped
     *         member.
     * @since 5.1
     */
    suspend fun bzpopmin(timeout: Long, vararg keys: K): KeyValue<K, ScoredValue<V>>?

    /**
     * Removes and returns a member with the highest scores in the sorted set stored at one of the keys.
     *
     * @param timeout the timeout in seconds.
     * @param keys the keys.
     * @return KeyValue<K, ScoredValue<V>> multi-bulk containing the name of the key, the score and the popped
     *         member.
     * @since 5.1
     */
    suspend fun bzpopmax(timeout: Long, vararg keys: K): KeyValue<K, ScoredValue<V>>?

    /**
     * Add one or more members to a sorted set, or update its score if it already exists.
     *
     * @param key the key.
     * @param score the score.
     * @param member the member.
     * @return Long integer-reply specifically:
     *
     *         The number of elements added to the sorted sets, not including elements already existing for which the score was
     *         updated.
     */
    suspend fun zadd(key: K, score: Double, member: V): Long?

    /**
     * Add one or more members to a sorted set, or update its score if it already exists.
     *
     * @param key the key.
     * @param scoresAndValues the scoresAndValue tuples (score,value,score,value,...).
     * @return Long integer-reply specifically:
     *
     *         The number of elements added to the sorted sets, not including elements already existing for which the score was
     *         updated.
     */
    suspend fun zadd(key: K, vararg scoresAndValues: Any): Long?

    /**
     * Add one or more members to a sorted set, or update its score if it already exists.
     *
     * @param key the key.
     * @param scoredValues the scored values.
     * @return Long integer-reply specifically:
     *
     *         The number of elements added to the sorted sets, not including elements already existing for which the score was
     *         updated.
     */
    suspend fun zadd(key: K, vararg scoredValues: ScoredValue<V>): Long?

    /**
     * Add one or more members to a sorted set, or update its score if it already exists.
     *
     * @param key the key.
     * @param zAddArgs arguments for zadd.
     * @param score the score.
     * @param member the member.
     * @return Long integer-reply specifically:
     *
     *         The number of elements added to the sorted sets, not including elements already existing for which the score was
     *         updated.
     */
    suspend fun zadd(key: K, zAddArgs: ZAddArgs, score: Double, member: V): Long?

    /**
     * Add one or more members to a sorted set, or update its score if it already exists.
     *
     * @param key the key.
     * @param zAddArgs arguments for zadd.
     * @param scoresAndValues the scoresAndValue tuples (score,value,score,value,...).
     * @return Long integer-reply specifically:
     *
     *         The number of elements added to the sorted sets, not including elements already existing for which the score was
     *         updated.
     */
    suspend fun zadd(key: K, zAddArgs: ZAddArgs, vararg scoresAndValues: Any): Long?

    /**
     * Add one or more members to a sorted set, or update its score if it already exists.
     *
     * @param key the ke.
     * @param zAddArgs arguments for zadd.
     * @param scoredValues the scored values.
     * @return Long integer-reply specifically:
     *
     *         The number of elements added to the sorted sets, not including elements already existing for which the score was
     *         updated.
     */
    suspend fun zadd(key: K, zAddArgs: ZAddArgs, vararg scoredValues: ScoredValue<V>): Long?

    /**
     * Add one or more members to a sorted set, or update its score if it already exists applying the `INCR` option. ZADD
     * acts like ZINCRBY.
     *
     * @param key the key.
     * @param score the score.
     * @param member the member.
     * @return Long integer-reply specifically: The total number of elements changed.
     */
    suspend fun zaddincr(key: K, score: Double, member: V): Double?

    /**
     * Add one or more members to a sorted set, or update its score if it already exists applying the `INCR` option. ZADD
     * acts like ZINCRBY.
     *
     * @param key the key.
     * @param zAddArgs arguments for zadd.
     * @param score the score.
     * @param member the member.
     * @return Long integer-reply specifically: The total number of elements changed.
     * @since 4.3
     */
    suspend fun zaddincr(key: K, zAddArgs: ZAddArgs, score: Double, member: V): Double?

    /**
     * Get the number of members in a sorted set.
     *
     * @param key the key.
     * @return Long integer-reply the cardinality (number of elements) of the sorted set, or `false` if `key` does
     *         not exist.
     */
    suspend fun zcard(key: K): Long?

    /**
     * Count the members in a sorted set with scores within the given [Range].
     *
     * @param key the key.
     * @param range the range.
     * @return Long integer-reply the number of elements in the specified score range.
     * @since 4.3
     */
    suspend fun zcount(key: K, range: Range<out Number>): Long?

    /**
     * Increment the score of a member in a sorted set.
     *
     * @param key the key.
     * @param amount the increment type: long.
     * @param member the member type: value.
     * @return Double bulk-string-reply the new score of `member` (a Double precision floating point number), represented
     *         as string.
     */
    suspend fun zincrby(key: K, amount: Double, member: V): Double?

    /**
     * Intersect multiple sorted sets and store the resulting sorted set in a new key.
     *
     * @param destination the destination.
     * @param keys the keys.
     * @return Long integer-reply the number of elements in the resulting sorted set at `destination`.
     */
    suspend fun zinterstore(destination: K, vararg keys: K): Long?

    /**
     * Intersect multiple sorted sets and store the resulting sorted set in a new key.
     *
     * @param destination the destination.
     * @param storeArgs the storeArgs.
     * @param keys the keys.
     * @return Long integer-reply the number of elements in the resulting sorted set at `destination`.
     */
    suspend fun zinterstore(destination: K, storeArgs: ZStoreArgs, vararg keys: K): Long?

    /**
     * Count the number of members in a sorted set between a given lexicographical range.
     *
     * @param key the key.
     * @param range the range.
     * @return Long integer-reply the number of elements in the specified score range.
     * @since 4.3
     */
    suspend fun zlexcount(key: K, range: Range<out V>): Long?

    /**
     * Removes and returns up to count members with the lowest scores in the sorted set stored at key.
     *
     * @param key the key.
     * @return ScoredValue<V> the removed element.
     * @since 5.1
     */
    suspend fun zpopmin(key: K): ScoredValue<V>?

    /**
     * Removes and returns up to count members with the lowest scores in the sorted set stored at key.
     *
     * @param key the key.
     * @param count the number of elements to return.
     * @return List<ScoredValue<V>> array-reply list of popped scores and elements.
     * @since 5.1
     */
    fun zpopmin(key: K, count: Long): Flow<ScoredValue<V>>

    /**
     * Removes and returns up to count members with the highest scores in the sorted set stored at key.
     *
     * @param key the key.
     * @return ScoredValue<V> the removed element.
     * @since 5.1
     */
    suspend fun zpopmax(key: K): ScoredValue<V>?

    /**
     * Removes and returns up to count members with the highest scores in the sorted set stored at key.
     *
     * @param key the key.
     * @param count the number of elements to return.
     * @return List<ScoredValue<V>> array-reply list of popped scores and elements.
     * @since 5.1
     */
    fun zpopmax(key: K, count: Long): Flow<ScoredValue<V>>

    /**
     * Return a range of members in a sorted set, by index.
     *
     * @param key the key.
     * @param start the start.
     * @param stop the stop.
     * @return List<V> array-reply list of elements in the specified range.
     */
    fun zrange(key: K, start: Long, stop: Long): Flow<V>

    /**
     * Return a range of members with scores in a sorted set, by index.
     *
     * @param key the key.
     * @param start the start.
     * @param stop the stop.
     * @return List<V> array-reply list of elements in the specified range.
     */
    fun zrangeWithScores(key: K, start: Long, stop: Long): Flow<ScoredValue<V>>

    /**
     * Return a range of members in a sorted set, by lexicographical range.
     *
     * @param key the key.
     * @param range the range.
     * @return List<V> array-reply list of elements in the specified range.
     * @since 4.3
     */
    fun zrangebylex(key: K, range: Range<out V>): Flow<V>

    /**
     * Return a range of members in a sorted set, by lexicographical range.
     *
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return List<V> array-reply list of elements in the specified range.
     * @since 4.3
     */
    fun zrangebylex(key: K, range: Range<out V>, limit: Limit): Flow<V>

    /**
     * Return a range of members in a sorted set, by score.
     *
     * @param key the key.
     * @param range the range.
     * @return List<V> array-reply list of elements in the specified score range.
     * @since 4.3
     */
    fun zrangebyscore(key: K, range: Range<out Number>): Flow<V>

    /**
     * Return a range of members in a sorted set, by score.
     *
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return List<V> array-reply list of elements in the specified score range.
     * @since 4.3
     */
    fun zrangebyscore(key: K, range: Range<out Number>, limit: Limit): Flow<V>

    /**
     * Return a range of members with score in a sorted set, by score.
     *
     * @param key the key.
     * @param range the range.
     * @return List<ScoredValue<V>> array-reply list of elements in the specified score range.
     * @since 4.3
     */
    fun zrangebyscoreWithScores(key: K, range: Range<out Number>): Flow<ScoredValue<V>>

    /**
     * Return a range of members with score in a sorted set, by score.
     *
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return List<ScoredValue<V>> array-reply list of elements in the specified score range.
     * @since 4.3
     */
    fun zrangebyscoreWithScores(key: K, range: Range<out Number>, limit: Limit): Flow<ScoredValue<V>>

    /**
     * Determine the index of a member in a sorted set.
     *
     * @param key the key.
     * @param member the member type: value.
     * @return Long integer-reply the rank of `member`. If `member` does not exist in the sorted set or `key`
     *         does not exist,.
     */
    suspend fun zrank(key: K, member: V): Long?

    /**
     * Remove one or more members from a sorted set.
     *
     * @param key the key.
     * @param members the member type: value.
     * @return Long integer-reply specifically:
     *
     *         The number of members removed from the sorted set, not including non existing members.
     */
    suspend fun zrem(key: K, vararg members: V): Long?

    /**
     * Remove all members in a sorted set between the given lexicographical range.
     *
     * @param key the key.
     * @param range the range.
     * @return Long integer-reply the number of elements removed.
     * @since 4.3
     */
    suspend fun zremrangebylex(key: K, range: Range<out V>): Long?

    /**
     * Remove all members in a sorted set within the given indexes.
     *
     * @param key the key.
     * @param start the start type: long.
     * @param stop the stop type: long.
     * @return Long integer-reply the number of elements removed.
     */
    suspend fun zremrangebyrank(key: K, start: Long, stop: Long): Long?

    /**
     * Remove all members in a sorted set within the given scores.
     *
     * @param key the key.
     * @param range the range.
     * @return Long integer-reply the number of elements removed.
     * @since 4.3
     */
    suspend fun zremrangebyscore(key: K, range: Range<out Number>): Long?

    /**
     * Return a range of members in a sorted set, by index, with scores ordered from high to low.
     *
     * @param key the key.
     * @param start the start.
     * @param stop the stop.
     * @return List<V> array-reply list of elements in the specified range.
     */
    fun zrevrange(key: K, start: Long, stop: Long): Flow<V>

    /**
     * Return a range of members with scores in a sorted set, by index, with scores ordered from high to low.
     *
     * @param key the key.
     * @param start the start.
     * @param stop the stop.
     * @return List<V> array-reply list of elements in the specified range.
     */
    fun zrevrangeWithScores(key: K, start: Long, stop: Long): Flow<ScoredValue<V>>

    /**
     * Return a range of members in a sorted set, by lexicographical range ordered from high to low.
     *
     * @param key the key.
     * @param range the range.
     * @return List<V> array-reply list of elements in the specified score range.
     * @since 4.3
     */
    fun zrevrangebylex(key: K, range: Range<out V>): Flow<V>

    /**
     * Return a range of members in a sorted set, by lexicographical range ordered from high to low.
     *
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return List<V> array-reply list of elements in the specified score range.
     * @since 4.3
     */
    fun zrevrangebylex(key: K, range: Range<out V>, limit: Limit): Flow<V>

    /**
     * Return a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param range the range.
     * @return List<V> array-reply list of elements in the specified score range.
     * @since 4.3
     */
    fun zrevrangebyscore(key: K, range: Range<out Number>): Flow<V>

    /**
     * Return a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return List<V> array-reply list of elements in the specified score range.
     * @since 4.3
     */
    fun zrevrangebyscore(key: K, range: Range<out Number>, limit: Limit): Flow<V>

    /**
     * Return a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param range the range.
     * @return List<ScoredValue<V>> array-reply list of elements in the specified score range.
     * @since 4.3
     */
    fun zrevrangebyscoreWithScores(key: K, range: Range<out Number>): Flow<ScoredValue<V>>

    /**
     * Return a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param range the range.
     * @param limit limit.
     * @return List<V> array-reply list of elements in the specified score range.
     * @since 4.3
     */
    fun zrevrangebyscoreWithScores(key: K, range: Range<out Number>, limit: Limit): Flow<ScoredValue<V>>

    /**
     * Determine the index of a member in a sorted set, with scores ordered from high to low.
     *
     * @param key the key.
     * @param member the member type: value.
     * @return Long integer-reply the rank of `member`. If `member` does not exist in the sorted set or `key`
     *         does not exist,.
     */
    suspend fun zrevrank(key: K, member: V): Long?

    /**
     * Incrementally iterate sorted sets elements and associated scores.
     *
     * @param key the key.
     * @return ScoredValueScanCursor<V> scan cursor.
     */
    suspend fun zscan(key: K): ScoredValueScanCursor<V>?

    /**
     * Incrementally iterate sorted sets elements and associated scores.
     *
     * @param key the key.
     * @param scanArgs scan arguments.
     * @return ScoredValueScanCursor<V> scan cursor.
     */
    suspend fun zscan(key: K, scanArgs: ScanArgs): ScoredValueScanCursor<V>?

    /**
     * Incrementally iterate sorted sets elements and associated scores.
     *
     * @param key the key.
     * @param scanCursor cursor to resume from a previous scan, must not be `null`.
     * @param scanArgs scan arguments.
     * @return ScoredValueScanCursor<V> scan cursor.
     */
    suspend fun zscan(key: K, scanCursor: ScanCursor, scanArgs: ScanArgs): ScoredValueScanCursor<V>?

    /**
     * Incrementally iterate sorted sets elements and associated scores.
     *
     * @param key the key.
     * @param scanCursor cursor to resume from a previous scan, must not be `null`.
     * @return ScoredValueScanCursor<V> scan cursor.
     */
    suspend fun zscan(key: K, scanCursor: ScanCursor): ScoredValueScanCursor<V>?

    /**
     * Get the score associated with the given member in a sorted set.
     *
     * @param key the key.
     * @param member the member type: value.
     * @return Double bulk-string-reply the score of `member` (a Double precision floating point number), represented as
     *         string.
     */
    suspend fun zscore(key: K, member: V): Double?

    /**
     * Add multiple sorted sets and store the resulting sorted set in a new key.
     *
     * @param destination destination key.
     * @param keys source keys.
     * @return Long integer-reply the number of elements in the resulting sorted set at `destination`.
     */
    suspend fun zunionstore(destination: K, vararg keys: K): Long?

    /**
     * Add multiple sorted sets and store the resulting sorted set in a new key.
     *
     * @param destination the destination.
     * @param storeArgs the storeArgs.
     * @param keys the keys.
     * @return Long integer-reply the number of elements in the resulting sorted set at `destination`.
     */
    suspend fun zunionstore(destination: K, storeArgs: ZStoreArgs, vararg keys: K): Long?

}

