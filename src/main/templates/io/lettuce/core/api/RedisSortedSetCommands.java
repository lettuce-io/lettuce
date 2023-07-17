/*
 * Copyright 2017-2022 the original author or authors.
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
package io.lettuce.core.api;

import java.util.List;

import io.lettuce.core.*;
import io.lettuce.core.output.ScoredValueStreamingChannel;
import io.lettuce.core.output.ValueStreamingChannel;

/**
 * ${intent} for Sorted Sets.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mark Paluch
 * @since 4.0
 */
public interface RedisSortedSetCommands<K, V> {

    /**
     * Pops one or more elements, that are member-score pairs, from the first non-empty sorted set in the provided list of keys.
     *
     * @param timeout the timeout in seconds.
     * @param keys the keys.
     * @return ScoredValue&lt;V&gt; the removed element or {@link KeyValue#empty()}.
     * @since 6.3
     */
    KeyValue<K, ScoredValue<V>> bzmpop(long timeout, ZPopArgs args, K... keys);

    /**
     * Pops one or more elements, that are member-score pairs, from the first non-empty sorted set in the provided list of keys.
     *
     * @param timeout the timeout in seconds.
     * @param count number of elements to pop.
     * @param args the command args.
     * @param keys the keys.
     * @return ScoredValue&lt;V&gt; the removed elements or {@link KeyValue#empty()}.
     * @since 6.3
     */
    KeyValue<K, List<ScoredValue<V>>> bzmpop(long timeout, long count, ZPopArgs args, K... keys);

    /**
     * Pops one or more elements, that are member-score pairs, from the first non-empty sorted set in the provided list of keys.
     *
     * @param timeout the timeout in seconds.
     * @param keys the keys.
     * @return ScoredValue&lt;V&gt; the removed element or {@link KeyValue#empty()}.
     * @since 6.3
     */
    KeyValue<K, ScoredValue<V>> bzmpop(double timeout, ZPopArgs args, K... keys);

    /**
     * Pops one or more elements, that are member-score pairs, from the first non-empty sorted set in the provided list of keys.
     *
     * @param timeout the timeout in seconds.
     * @param count number of elements to pop.
     * @param args the command args.
     * @param keys the keys.
     * @return ScoredValue&lt;V&gt; the removed elements or {@link KeyValue#empty()}.
     * @since 6.3
     */
    KeyValue<K, List<ScoredValue<V>>> bzmpop(double timeout, int count, ZPopArgs args, K... keys);

    /**
     * Removes and returns a member with the lowest scores in the sorted set stored at one of the keys.
     *
     * @param timeout the timeout in seconds.
     * @param keys the keys.
     * @return KeyValue&lt;K, ScoredValue&lt;V&gt;&gt; multi-bulk containing the name of the key, the score and the popped
     *         member.
     * @since 5.1
     */
    KeyValue<K, ScoredValue<V>> bzpopmin(long timeout, K... keys);

    /**
     * Removes and returns a member with the lowest scores in the sorted set stored at one of the keys.
     *
     * @param timeout the timeout in seconds.
     * @param keys the keys.
     * @return KeyValue&lt;K, ScoredValue&lt;V&gt;&gt; multi-bulk containing the name of the key, the score and the popped
     *         member.
     * @since 6.1.3
     */
    KeyValue<K, ScoredValue<V>> bzpopmin(double timeout, K... keys);

    /**
     * Removes and returns a member with the highest scores in the sorted set stored at one of the keys.
     *
     * @param timeout the timeout in seconds.
     * @param keys the keys.
     * @return KeyValue&lt;K, ScoredValue&lt;V&gt;&gt; multi-bulk containing the name of the key, the score and the popped
     *         member.
     * @since 5.1
     */
    KeyValue<K, ScoredValue<V>> bzpopmax(long timeout, K... keys);

    /**
     * Removes and returns a member with the highest scores in the sorted set stored at one of the keys.
     *
     * @param timeout the timeout in seconds.
     * @param keys the keys.
     * @return KeyValue&lt;K, ScoredValue&lt;V&gt;&gt; multi-bulk containing the name of the key, the score and the popped
     *         member.
     * @since 6.1.3
     */
    KeyValue<K, ScoredValue<V>> bzpopmax(double timeout, K... keys);

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
    Long zadd(K key, double score, V member);

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
    Long zadd(K key, Object... scoresAndValues);

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
    Long zadd(K key, ScoredValue<V>... scoredValues);

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
    Long zadd(K key, ZAddArgs zAddArgs, double score, V member);

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
    Long zadd(K key, ZAddArgs zAddArgs, Object... scoresAndValues);

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
    Long zadd(K key, ZAddArgs zAddArgs, ScoredValue<V>... scoredValues);

    /**
     * Add one or more members to a sorted set, or update its score if it already exists applying the {@code INCR} option. ZADD
     * acts like ZINCRBY.
     *
     * @param key the key.
     * @param score the score.
     * @param member the member.
     * @return Long integer-reply specifically: The total number of elements changed.
     */
    Double zaddincr(K key, double score, V member);

    /**
     * Add one or more members to a sorted set, or update its score if it already exists applying the {@code INCR} option. ZADD
     * acts like ZINCRBY.
     *
     * @param key the key.
     * @param zAddArgs arguments for zadd.
     * @param score the score.
     * @param member the member.
     * @return Long integer-reply specifically: The total number of elements changed.
     * @since 4.3
     */
    Double zaddincr(K key, ZAddArgs zAddArgs, double score, V member);

    /**
     * Get the number of members in a sorted set.
     *
     * @param key the key.
     * @return Long integer-reply the cardinality (number of elements) of the sorted set, or {@code false} if {@code key} does
     *         not exist.
     */
    Long zcard(K key);

    /**
     * Count the members in a sorted set with scores within the given values.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long integer-reply the number of elements in the specified score range.
     * @deprecated Use {@link #zcount(java.lang.Object, Range)}.
     */
    @Deprecated
    Long zcount(K key, double min, double max);

    /**
     * Count the members in a sorted set with scores within the given values.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long integer-reply the number of elements in the specified score range.
     * @deprecated Use {@link #zcount(java.lang.Object, Range)}.
     */
    @Deprecated
    Long zcount(K key, String min, String max);

    /**
     * Count the members in a sorted set with scores within the given {@link Range}.
     *
     * @param key the key.
     * @param range the range.
     * @return Long integer-reply the number of elements in the specified score range.
     * @since 4.3
     */
    Long zcount(K key, Range<? extends Number> range);

    /**
     * Computes the difference between the first and all successive input sorted sets.
     *
     * @param keys the keys.
     * @return List&lt;V&gt; array-reply list of elements.
     * @since 6.1
     */
    List<V> zdiff(K... keys);

    /**
     * Computes the difference between the first and all successive input sorted sets and stores the result in destination.
     *
     * @param destKey the dest key.
     * @param srcKeys the src keys.
     * @return Long the number of elements in the resulting sorted set at destination.
     * @since 6.1
     */
    Long zdiffstore(K destKey, K... srcKeys);

    /**
     * Computes the difference between the first and all successive input sorted sets.
     *
     * @param keys the keys.
     * @return List&lt;V&gt; array-reply list of scored values.
     * @since 6.1
     */
    List<ScoredValue<V>> zdiffWithScores(K... keys);

    /**
     * Increment the score of a member in a sorted set.
     *
     * @param key the key.
     * @param amount the increment type: long.
     * @param member the member type: value.
     * @return Double bulk-string-reply the new score of {@code member} (a double precision floating point number), represented
     *         as string.
     */
    Double zincrby(K key, double amount, V member);

    /**
     * Intersect multiple sorted sets and returns the resulting sorted.
     *
     * @param keys the keys.
     * @return List&lt;V&gt; array-reply list of elements.
     * @since 6.1
     */
    List<V> zinter(K... keys);

    /**
     * Intersect multiple sorted sets and returns the resulting sorted.
     *
     * @param aggregateArgs arguments to define aggregation and weights.
     * @param keys the keys.
     * @return List&lt;V&gt; array-reply list of elements.
     * @since 6.1
     */
    List<V> zinter(ZAggregateArgs aggregateArgs, K... keys);

    /**
     * This command is similar to {@link #zinter(java.lang.Object[])}, but instead of returning the result set, it returns just
     * the cardinality of the result.
     *
     * @param keys the keys.
     * @return Long Integer reply the number of elements in the resulting intersection.
     * @since 6.2
     */
    Long zintercard(K... keys);

    /**
     * This command is similar to {@link #zinter(java.lang.Object[])}, but instead of returning the result set, it returns just
     * the cardinality of the result.
     *
     * @param limit If the intersection cardinality reaches limit partway through the computation, the algorithm will exit and
     *        yield limit as the cardinality
     * @param keys the keys.
     * @return Long Integer reply the number of elements in the resulting intersection.
     * @since 6.2
     */
    Long zintercard(long limit, K... keys);

    /**
     * Intersect multiple sorted sets and returns the resulting sorted.
     *
     * @param aggregateArgs arguments to define aggregation and weights.
     * @param keys the keys.
     * @return List&lt;V&gt; array-reply list of scored values.
     * @since 6.1
     */
    List<ScoredValue<V>> zinterWithScores(ZAggregateArgs aggregateArgs, K... keys);

    /**
     * Intersect multiple sorted sets and returns the resulting sorted.
     *
     * @param keys the keys.
     * @return List&lt;V&gt; array-reply list of scored values.
     * @since 6.1
     */
    List<ScoredValue<V>> zinterWithScores(K... keys);

    /**
     * Intersect multiple sorted sets and store the resulting sorted set in a new key.
     *
     * @param destination the destination.
     * @param keys the keys.
     * @return Long integer-reply the number of elements in the resulting sorted set at {@code destination}.
     */
    Long zinterstore(K destination, K... keys);

    /**
     * Intersect multiple sorted sets and store the resulting sorted set in a new key.
     *
     * @param destination the destination.
     * @param storeArgs arguments to define aggregation and weights.
     * @param keys the keys.
     * @return Long integer-reply the number of elements in the resulting sorted set at {@code destination}.
     */
    Long zinterstore(K destination, ZStoreArgs storeArgs, K... keys);

    /**
     * Count the number of members in a sorted set between a given lexicographical range.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long integer-reply the number of elements in the specified score range.
     * @deprecated Use {@link #zlexcount(java.lang.Object, Range)}.
     */
    @Deprecated
    Long zlexcount(K key, String min, String max);

    /**
     * Count the number of members in a sorted set between a given lexicographical range.
     *
     * @param key the key.
     * @param range the range.
     * @return Long integer-reply the number of elements in the specified score range.
     * @since 4.3
     */
    Long zlexcount(K key, Range<? extends V> range);

    /**
     * Returns the scores associated with the specified members in the sorted set stored at key.
     *
     * @param key the key.
     * @param members the member type: value.
     * @return List&lt;Double&gt; array-reply list of scores or nil associated with the specified member values.
     * @since 6.1
     */
    List<Double> zmscore(K key, V... members);

    /**
     * Pops one or more elements, that are member-score pairs, from the first non-empty sorted set in the provided list of keys.
     *
     * @param keys the keys.
     * @return ScoredValue&lt;V&gt; the removed element or {@link KeyValue#empty()}.
     * @since 6.3
     */
    KeyValue<K, ScoredValue<V>> zmpop(ZPopArgs args, K... keys);

    /**
     * Pops one or more elements, that are member-score pairs, from the first non-empty sorted set in the provided list of keys.
     *
     * @param count number of elements to pop.
     * @param args the command args.
     * @param keys the keys.
     * @return ScoredValue&lt;V&gt; the removed elements or {@link KeyValue#empty()}.
     * @since 6.3
     */
    KeyValue<K, List<ScoredValue<V>>> zmpop(int count, ZPopArgs args, K... keys);

    /**
     * Removes and returns up to count members with the lowest scores in the sorted set stored at key.
     *
     * @param key the key.
     * @return ScoredValue&lt;V&gt; the removed element.
     * @since 5.1
     */
    ScoredValue<V> zpopmin(K key);

    /**
     * Removes and returns up to count members with the lowest scores in the sorted set stored at key.
     *
     * @param key the key.
     * @param count the number of elements to return.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of popped scores and elements.
     * @since 5.1
     */
    List<ScoredValue<V>> zpopmin(K key, long count);

    /**
     * Removes and returns up to count members with the highest scores in the sorted set stored at key.
     *
     * @param key the key.
     * @return ScoredValue&lt;V&gt; the removed element.
     * @since 5.1
     */
    ScoredValue<V> zpopmax(K key);

    /**
     * Removes and returns up to count members with the highest scores in the sorted set stored at key.
     *
     * @param key the key.
     * @param count the number of elements to return.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of popped scores and elements.
     * @since 5.1
     */
    List<ScoredValue<V>> zpopmax(K key, long count);

    /**
     * Return a random member from the sorted set stored at {@code key}.
     *
     * @param key the key.
     * @return element.
     * @since 6.1
     */
    V zrandmember(K key);

    /**
     * Return {@code count} random members from the sorted set stored at {@code key}.
     *
     * @param key the key.
     * @param count the number of members to return. If the provided count argument is positive, return an array of distinct fields.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of scores and elements.
     * @since 6.1
     */
    List<V> zrandmember(K key, long count);

    /**
     * Return a random member along its value from the sorted set stored at {@code key}.
     *
     * @param key the key.
     * @return the score and element.
     * @since 6.1
     */
    ScoredValue<V> zrandmemberWithScores(K key);

    /**
     * Return {@code count} random members along their value from the sorted set stored at {@code key}.
     *
     * @param key the key.
     * @param count the number of members to return. If the provided count argument is positive, return an array of distinct fields.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of scores and elements.
     * @since 6.1
     */
    List<ScoredValue<V>> zrandmemberWithScores(K key, long count);

    /**
     * Return a range of members in a sorted set, by index.
     *
     * @param key the key.
     * @param start the start.
     * @param stop the stop.
     * @return List&lt;V&gt; array-reply list of elements in the specified range.
     */
    List<V> zrange(K key, long start, long stop);

    /**
     * Return a range of members in a sorted set, by index.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param start the start.
     * @param stop the stop.
     * @return Long count of elements in the specified range.
     */
    Long zrange(ValueStreamingChannel<V> channel, K key, long start, long stop);

    /**
     * Return a range of members with scores in a sorted set, by index.
     *
     * @param key the key.
     * @param start the start.
     * @param stop the stop.
     * @return List&lt;V&gt; array-reply list of elements in the specified range.
     */
    List<ScoredValue<V>> zrangeWithScores(K key, long start, long stop);

    /**
     * Stream over a range of members with scores in a sorted set, by index.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param start the start.
     * @param stop the stop.
     * @return Long count of elements in the specified range.
     */
    Long zrangeWithScores(ScoredValueStreamingChannel<V> channel, K key, long start, long stop);

    /**
     * Return a range of members in a sorted set, by lexicographical range.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return List&lt;V&gt; array-reply list of elements in the specified range.
     * @deprecated Use {@link #zrangebylex(java.lang.Object, Range)}.
     */
    @Deprecated
    List<V> zrangebylex(K key, String min, String max);

    /**
     * Return a range of members in a sorted set, by lexicographical range.
     *
     * @param key the key.
     * @param range the range.
     * @return List&lt;V&gt; array-reply list of elements in the specified range.
     * @since 4.3
     */
    List<V> zrangebylex(K key, Range<? extends V> range);

    /**
     * Return a range of members in a sorted set, by lexicographical range.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return List&lt;V&gt; array-reply list of elements in the specified range.
     * @deprecated Use {@link #zrangebylex(java.lang.Object, Range)}.
     */
    @Deprecated
    List<V> zrangebylex(K key, String min, String max, long offset, long count);

    /**
     * Return a range of members in a sorted set, by lexicographical range.
     *
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return List&lt;V&gt; array-reply list of elements in the specified range.
     * @since 4.3
     */
    List<V> zrangebylex(K key, Range<? extends V> range, Limit limit);

    /**
     * Return a range of members in a sorted set, by score.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscore(java.lang.Object, Range)}.
     */
    @Deprecated
    List<V> zrangebyscore(K key, double min, double max);

    /**
     * Return a range of members in a sorted set, by score.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscore(java.lang.Object, Range)}.
     */
    @Deprecated
    List<V> zrangebyscore(K key, String min, String max);

    /**
     * Return a range of members in a sorted set, by score.
     *
     * @param key the key.
     * @param range the range.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @since 4.3
     */
    List<V> zrangebyscore(K key, Range<? extends Number> range);

    /**
     * Return a range of members in a sorted set, by score.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscore(java.lang.Object, Range, Limit)}.
     */
    @Deprecated
    List<V> zrangebyscore(K key, double min, double max, long offset, long count);

    /**
     * Return a range of members in a sorted set, by score.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscore(java.lang.Object, Range, Limit)}.
     */
    @Deprecated
    List<V> zrangebyscore(K key, String min, String max, long offset, long count);

    /**
     * Return a range of members in a sorted set, by score.
     *
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @since 4.3
     */
    List<V> zrangebyscore(K key, Range<? extends Number> range, Limit limit);

    /**
     * Stream over a range of members in a sorted set, by score.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long count of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscore(ValueStreamingChannel, java.lang.Object, Range)}.
     */
    @Deprecated
    Long zrangebyscore(ValueStreamingChannel<V> channel, K key, double min, double max);

    /**
     * Stream over a range of members in a sorted set, by score.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long count of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscore(ValueStreamingChannel, java.lang.Object, Range)}.
     */
    @Deprecated
    Long zrangebyscore(ValueStreamingChannel<V> channel, K key, String min, String max);

    /**
     * Stream over a range of members in a sorted set, by score.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param range the range.
     * @return Long count of elements in the specified score range.
     * @since 4.3
     */
    Long zrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range);

    /**
     * Stream over range of members in a sorted set, by score.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return Long count of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscore(ValueStreamingChannel, java.lang.Object, Range, Limit limit)}.
     */
    @Deprecated
    Long zrangebyscore(ValueStreamingChannel<V> channel, K key, double min, double max, long offset, long count);

    /**
     * Stream over a range of members in a sorted set, by score.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return Long count of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscore(ValueStreamingChannel, java.lang.Object, Range, Limit limit)}.
     */
    @Deprecated
    Long zrangebyscore(ValueStreamingChannel<V> channel, K key, String min, String max, long offset, long count);

    /**
     * Stream over a range of members in a sorted set, by score.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return Long count of elements in the specified score range.
     * @since 4.3
     */
    Long zrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit);

    /**
     * Return a range of members with score in a sorted set, by score.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscoreWithScores(java.lang.Object, Range)}.
     */
    @Deprecated
    List<ScoredValue<V>> zrangebyscoreWithScores(K key, double min, double max);

    /**
     * Return a range of members with score in a sorted set, by score.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscoreWithScores(java.lang.Object, Range)}.
     */
    @Deprecated
    List<ScoredValue<V>> zrangebyscoreWithScores(K key, String min, String max);

    /**
     * Return a range of members with score in a sorted set, by score.
     *
     * @param key the key.
     * @param range the range.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of elements in the specified score range.
     * @since 4.3
     */
    List<ScoredValue<V>> zrangebyscoreWithScores(K key, Range<? extends Number> range);

    /**
     * Return a range of members with score in a sorted set, by score.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscoreWithScores(java.lang.Object, Range, Limit limit)}.
     */
    @Deprecated
    List<ScoredValue<V>> zrangebyscoreWithScores(K key, double min, double max, long offset, long count);

    /**
     * Return a range of members with score in a sorted set, by score.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscoreWithScores(java.lang.Object, Range, Limit)}.
     */
    @Deprecated
    List<ScoredValue<V>> zrangebyscoreWithScores(K key, String min, String max, long offset, long count);

    /**
     * Return a range of members with score in a sorted set, by score.
     *
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of elements in the specified score range.
     * @since 4.3
     */
    List<ScoredValue<V>> zrangebyscoreWithScores(K key, Range<? extends Number> range, Limit limit);

    /**
     * Stream over a range of members with scores in a sorted set, by score.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long count of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscoreWithScores(ScoredValueStreamingChannel, java.lang.Object, Range)}.
     */
    @Deprecated
    Long zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double min, double max);

    /**
     * Stream over a range of members with scores in a sorted set, by score.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long count of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscoreWithScores(ScoredValueStreamingChannel, java.lang.Object, Range)}.
     */
    @Deprecated
    Long zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String min, String max);

    /**
     * Stream over a range of members with scores in a sorted set, by score.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param range the range.
     * @return Long count of elements in the specified score range.
     * @since 4.3
     */
    Long zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range);

    /**
     * Stream over a range of members with scores in a sorted set, by score.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return Long count of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscoreWithScores(ScoredValueStreamingChannel, java.lang.Object, Range, Limit limit)}.
     */
    @Deprecated
    Long zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double min, double max, long offset,
            long count);

    /**
     * Stream over a range of members with scores in a sorted set, by score.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return Long count of elements in the specified score range.
     * @deprecated Use {@link #zrangebyscoreWithScores(ScoredValueStreamingChannel, java.lang.Object, Range, Limit limit)}.
     */
    @Deprecated
    Long zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String min, String max, long offset,
            long count);

    /**
     * Stream over a range of members with scores in a sorted set, by score.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return Long count of elements in the specified score range.
     * @since 4.3
     */
    Long zrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit);

    /**
     * Get the specified range of elements in the sorted set stored at {@code srcKey} and stores the result in the {@code dstKey} destination key.
     *
     * @param dstKey the dst key.
     * @param srcKey the src key.
     * @param range the rank.
     * @return the number of elements in the resulting sorted set.
     * @since 6.2.1
     */
    Long zrangestore(K dstKey, K srcKey, Range<Long> range);

    /**
     * Get the specified range of elements in the sorted set stored at {@code srcKey} and stores the result in the {@code dstKey} destination key.
     *
     * @param dstKey the dst key.
     * @param srcKey the src key.
     * @param range the lexicographical range.
     * @param limit the limit to apply.
     * @return the number of elements in the resulting sorted set.
     * @since 6.1
     */
    Long zrangestorebylex(K dstKey, K srcKey, Range<? extends V> range, Limit limit);

    /**
     * Get the specified range of elements in the sorted set stored at {@code srcKey} and stores the result in the {@code dstKey} destination key.
     *
     * @param dstKey the dst key.
     * @param srcKey the src key.
     * @param range the score range.
     * @param limit the limit to apply.
     * @return the number of elements in the resulting sorted set.
     * @since 6.1
     */
    Long zrangestorebyscore(K dstKey, K srcKey, Range<? extends Number> range, Limit limit);

    /**
     * Determine the index of a member in a sorted set.
     *
     * @param key the key.
     * @param member the member type: value.
     * @return Long integer-reply the rank of {@code member}. If {@code member} does not exist in the sorted set or {@code key}
     *         does not exist,.
     */
    Long zrank(K key, V member);

    /**
     * Returns the rank of member in the sorted set stored at key, with the scores ordered from low to high.
     *
     * @param key the key.
     * @param member the member type: value.
     * @return the rank and score
     * @since 6.3
     */
    ScoredValue<Long> zrankWithScore(K key, V member);

    /**
     * Remove one or more members from a sorted set.
     *
     * @param key the key.
     * @param members the member type: value.
     * @return Long integer-reply specifically:
     *
     *         The number of members removed from the sorted set, not including non existing members.
     */
    Long zrem(K key, V... members);

    /**
     * Remove all members in a sorted set between the given lexicographical range.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long integer-reply the number of elements removed.
     * @deprecated Use {@link #zremrangebylex(java.lang.Object, Range)}.
     */
    @Deprecated
    Long zremrangebylex(K key, String min, String max);

    /**
     * Remove all members in a sorted set between the given lexicographical range.
     *
     * @param key the key.
     * @param range the range.
     * @return Long integer-reply the number of elements removed.
     * @since 4.3
     */
    Long zremrangebylex(K key, Range<? extends V> range);

    /**
     * Remove all members in a sorted set within the given indexes.
     *
     * @param key the key.
     * @param start the start type: long.
     * @param stop the stop type: long.
     * @return Long integer-reply the number of elements removed.
     */
    Long zremrangebyrank(K key, long start, long stop);

    /**
     * Remove all members in a sorted set within the given scores.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long integer-reply the number of elements removed.
     * @deprecated Use {@link #zremrangebyscore(java.lang.Object, Range)}.
     */
    @Deprecated
    Long zremrangebyscore(K key, double min, double max);

    /**
     * Remove all members in a sorted set within the given scores.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long integer-reply the number of elements removed.
     * @deprecated Use {@link #zremrangebyscore(java.lang.Object, Range)}.
     */
    @Deprecated
    Long zremrangebyscore(K key, String min, String max);

    /**
     * Remove all members in a sorted set within the given scores.
     *
     * @param key the key.
     * @param range the range.
     * @return Long integer-reply the number of elements removed.
     * @since 4.3
     */
    Long zremrangebyscore(K key, Range<? extends Number> range);

    /**
     * Return a range of members in a sorted set, by index, with scores ordered from high to low.
     *
     * @param key the key.
     * @param start the start.
     * @param stop the stop.
     * @return List&lt;V&gt; array-reply list of elements in the specified range.
     */
    List<V> zrevrange(K key, long start, long stop);

    /**
     * Stream over a range of members in a sorted set, by index, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param start the start.
     * @param stop the stop.
     * @return Long count of elements in the specified range.
     */
    Long zrevrange(ValueStreamingChannel<V> channel, K key, long start, long stop);

    /**
     * Return a range of members with scores in a sorted set, by index, with scores ordered from high to low.
     *
     * @param key the key.
     * @param start the start.
     * @param stop the stop.
     * @return List&lt;V&gt; array-reply list of elements in the specified range.
     */
    List<ScoredValue<V>> zrevrangeWithScores(K key, long start, long stop);

    /**
     * Stream over a range of members with scores in a sorted set, by index, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param start the start.
     * @param stop the stop.
     * @return Long count of elements in the specified range.
     */
    Long zrevrangeWithScores(ScoredValueStreamingChannel<V> channel, K key, long start, long stop);

    /**
     * Return a range of members in a sorted set, by lexicographical range ordered from high to low.
     *
     * @param key the key.
     * @param range the range.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @since 4.3
     */
    List<V> zrevrangebylex(K key, Range<? extends V> range);

    /**
     * Return a range of members in a sorted set, by lexicographical range ordered from high to low.
     *
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @since 4.3
     */
    List<V> zrevrangebylex(K key, Range<? extends V> range, Limit limit);

    /**
     * Return a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrevrangebyscore(java.lang.Object, Range)}.
     */
    @Deprecated
    List<V> zrevrangebyscore(K key, double max, double min);

    /**
     * Return a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrevrangebyscore(java.lang.Object, Range)}.
     */
    @Deprecated
    List<V> zrevrangebyscore(K key, String max, String min);

    /**
     * Return a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param range the range.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @since 4.3
     */
    List<V> zrevrangebyscore(K key, Range<? extends Number> range);

    /**
     * Return a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param max max score.
     * @param min min score.
     * @param offset the withscores.
     * @param count the number of items.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrevrangebyscore(java.lang.Object, Range, Limit)}.
     */
    @Deprecated
    List<V> zrevrangebyscore(K key, double max, double min, long offset, long count);

    /**
     * Return a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param max max score.
     * @param min min score.
     * @param offset the offset.
     * @param count the count.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrevrangebyscore(java.lang.Object, Range, Limit)}.
     */
    @Deprecated
    List<V> zrevrangebyscore(K key, String max, String min, long offset, long count);

    /**
     * Return a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @since 4.3
     */
    List<V> zrevrangebyscore(K key, Range<? extends Number> range, Limit limit);

    /**
     * Stream over a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param max max score.
     * @param min min score.
     * @return Long count of elements in the specified range.
     * @deprecated Use {@link #zrevrangebyscore(java.lang.Object, Range)}.
     */
    @Deprecated
    Long zrevrangebyscore(ValueStreamingChannel<V> channel, K key, double max, double min);

    /**
     * Stream over a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long count of elements in the specified range.
     * @deprecated Use {@link #zrevrangebyscore(java.lang.Object, Range)}.
     */
    @Deprecated
    Long zrevrangebyscore(ValueStreamingChannel<V> channel, K key, String max, String min);

    /**
     * Stream over a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param range the range.
     * @return Long count of elements in the specified range.
     * @since 4.3
     */
    Long zrevrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range);

    /**
     * Stream over a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return Long count of elements in the specified range.
     * @deprecated Use {@link #zrevrangebyscoreWithScores(java.lang.Object, Range, Limit)}.
     */
    @Deprecated
    Long zrevrangebyscore(ValueStreamingChannel<V> channel, K key, double max, double min, long offset, long count);

    /**
     * Stream over a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return Long count of elements in the specified range.
     * @deprecated Use {@link #zrevrangebyscoreWithScores(java.lang.Object, Range, Limit)}.
     */
    @Deprecated
    Long zrevrangebyscore(ValueStreamingChannel<V> channel, K key, String max, String min, long offset, long count);

    /**
     * Stream over a range of members in a sorted set, by score, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return Long count of elements in the specified range.
     * @since 4.3
     */
    Long zrevrangebyscore(ValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit);

    /**
     * Return a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param max max score.
     * @param min min score.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrevrangebyscoreWithScores(java.lang.Object, Range)}.
     */
    @Deprecated
    List<ScoredValue<V>> zrevrangebyscoreWithScores(K key, double max, double min);

    /**
     * Return a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param max max score.
     * @param min min score.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrevrangebyscoreWithScores(java.lang.Object, Range)}.
     */
    @Deprecated
    List<ScoredValue<V>> zrevrangebyscoreWithScores(K key, String max, String min);

    /**
     * Return a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param range the range.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of elements in the specified score range.
     * @since 4.3
     */
    List<ScoredValue<V>> zrevrangebyscoreWithScores(K key, Range<? extends Number> range);

    /**
     * Return a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param max max score.
     * @param min min score.
     * @param offset the offset.
     * @param count the count.
     * @return List&lt;ScoredValue&lt;V&gt;&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrevrangebyscoreWithScores(java.lang.Object, Range, Limit)}.
     */
    @Deprecated
    List<ScoredValue<V>> zrevrangebyscoreWithScores(K key, double max, double min, long offset, long count);

    /**
     * Return a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param max max score.
     * @param min min score.
     * @param offset the offset.
     * @param count the count.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @deprecated Use {@link #zrevrangebyscoreWithScores(java.lang.Object, Range, Limit)}.
     */
    @Deprecated
    List<ScoredValue<V>> zrevrangebyscoreWithScores(K key, String max, String min, long offset, long count);

    /**
     * Return a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param key the key.
     * @param range the range.
     * @param limit limit.
     * @return List&lt;V&gt; array-reply list of elements in the specified score range.
     * @since 4.3
     */
    List<ScoredValue<V>> zrevrangebyscoreWithScores(K key, Range<? extends Number> range, Limit limit);

    /**
     * Stream over a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long count of elements in the specified range.
     * @deprecated Use {@link #zrevrangebyscoreWithScores(ScoredValueStreamingChannel, java.lang.Object, Range)}.
     */
    @Deprecated
    Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double max, double min);

    /**
     * Stream over a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @return Long count of elements in the specified range.
     * @deprecated Use {@link #zrevrangebyscoreWithScores(ScoredValueStreamingChannel, java.lang.Object, Range)}.
     */
    @Deprecated
    Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String max, String min);

    /**
     * Stream over a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param range the range.
     * @return Long count of elements in the specified range.
     */
    Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range);

    /**
     * Stream over a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return Long count of elements in the specified range.
     * @deprecated Use {@link #zrevrangebyscoreWithScores(ScoredValueStreamingChannel, java.lang.Object, Range, Limit)}.
     */
    @Deprecated
    Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, double max, double min, long offset,
            long count);

    /**
     * Stream over a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param min min score.
     * @param max max score.
     * @param offset the offset.
     * @param count the count.
     * @return Long count of elements in the specified range.
     * @deprecated Use {@link #zrevrangebyscoreWithScores(ScoredValueStreamingChannel, java.lang.Object, Range, Limit)}.
     */
    @Deprecated
    Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, String max, String min, long offset,
            long count);

    /**
     * Stream over a range of members with scores in a sorted set, by score, with scores ordered from high to low.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param range the range.
     * @param limit the limit.
     * @return Long count of elements in the specified range.
     * @since 4.3
     */
    Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel<V> channel, K key, Range<? extends Number> range, Limit limit);

    /**
     * Get the specified range of elements ordered from high to low in the sorted set stored at {@code srcKey} and stores the result in the {@code dstKey} destination key.
     *
     * @param dstKey the dst key.
     * @param srcKey the src key.
     * @param range the rank.
     * @return the number of elements in the resulting sorted set.
     * @since 6.2.1
     */
    Long zrevrangestore(K dstKey, K srcKey, Range<Long> range);

    /**
     * Get the lexicographical range ordered from high to low of elements in the sorted set stored at {@code srcKey} and stores the result in the {@code dstKey} destination key.
     *
     * @param dstKey the src key.
     * @param srcKey the dst key.
     * @param range the lexicographical range.
     * @param limit the limit to apply.
     * @return the number of elements in the resulting sorted set.
     * @since 6.1
     */
    Long zrevrangestorebylex(K dstKey, K srcKey, Range<? extends V> range, Limit limit);

    /**
     * Get the specified range of elements in the sorted set stored at {@code srcKey with scores ordered from high to low and stores the result in the {@code dstKey} destination key.
     *
     * @param dstKey the src key.
     * @param srcKey the dst key.
     * @param range the score range.
     * @param limit the limit to apply.
     * @return the number of elements in the resulting sorted set.
     * @since 6.1
     */
    Long zrevrangestorebyscore(K dstKey, K srcKey, Range<? extends Number> range, Limit limit);

    /**
     * Determine the index of a member in a sorted set, with scores ordered from high to low.
     *
     * @param key the key.
     * @param member the member type: value.
     * @return Long integer-reply the rank of {@code member}. If {@code member} does not exist in the sorted set or {@code key}
     *         does not exist return {@code null}.
     */
    Long zrevrank(K key, V member);

    /**
     * Returns the rank of member in the sorted set stored at key, with the scores ordered from high to low.
     *
     * @param key the key.
     * @param member the member type: value.
     * @return the rank and score
     * @since 6.3
     */
    ScoredValue<Long> zrevrankWithScore(K key, V member);

    /**
     * Incrementally iterate sorted sets elements and associated scores.
     *
     * @param key the key.
     * @return ScoredValueScanCursor&lt;V&gt; scan cursor.
     */
    ScoredValueScanCursor<V> zscan(K key);

    /**
     * Incrementally iterate sorted sets elements and associated scores.
     *
     * @param key the key.
     * @param scanArgs scan arguments.
     * @return ScoredValueScanCursor&lt;V&gt; scan cursor.
     */
    ScoredValueScanCursor<V> zscan(K key, ScanArgs scanArgs);

    /**
     * Incrementally iterate sorted sets elements and associated scores.
     *
     * @param key the key.
     * @param scanCursor cursor to resume from a previous scan, must not be {@code null}.
     * @param scanArgs scan arguments.
     * @return ScoredValueScanCursor&lt;V&gt; scan cursor.
     */
    ScoredValueScanCursor<V> zscan(K key, ScanCursor scanCursor, ScanArgs scanArgs);

    /**
     * Incrementally iterate sorted sets elements and associated scores.
     *
     * @param key the key.
     * @param scanCursor cursor to resume from a previous scan, must not be {@code null}.
     * @return ScoredValueScanCursor&lt;V&gt; scan cursor.
     */
    ScoredValueScanCursor<V> zscan(K key, ScanCursor scanCursor);

    /**
     * Incrementally iterate sorted sets elements and associated scores.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @return StreamScanCursor scan cursor.
     */
    StreamScanCursor zscan(ScoredValueStreamingChannel<V> channel, K key);

    /**
     * Incrementally iterate sorted sets elements and associated scores.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param scanArgs scan arguments.
     * @return StreamScanCursor scan cursor.
     */
    StreamScanCursor zscan(ScoredValueStreamingChannel<V> channel, K key, ScanArgs scanArgs);

    /**
     * Incrementally iterate sorted sets elements and associated scores.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param scanCursor cursor to resume from a previous scan, must not be {@code null}.
     * @param scanArgs scan arguments.
     * @return StreamScanCursor scan cursor.
     */
    StreamScanCursor zscan(ScoredValueStreamingChannel<V> channel, K key, ScanCursor scanCursor, ScanArgs scanArgs);

    /**
     * Incrementally iterate sorted sets elements and associated scores.
     *
     * @param channel streaming channel that receives a call for every scored value.
     * @param key the key.
     * @param scanCursor cursor to resume from a previous scan, must not be {@code null}.
     * @return StreamScanCursor scan cursor.
     */
    StreamScanCursor zscan(ScoredValueStreamingChannel<V> channel, K key, ScanCursor scanCursor);

    /**
     * Get the score associated with the given member in a sorted set.
     *
     * @param key the key.
     * @param member the member type: value.
     * @return Double bulk-string-reply the score of {@code member} (a double precision floating point number), represented as
     *         string.
     */
    Double zscore(K key, V member);

    /**
     * Add multiple sorted sets and returns the resulting sorted set.
     *
     * @param keys the keys.
     * @return List&lt;V&gt; array-reply list of elements.
     * @since 6.1
     */
    List<V> zunion(K... keys);

    /**
     * Add multiple sorted sets and returns the resulting sorted set.
     *
     * @param aggregateArgs arguments to define aggregation and weights.
     * @param keys the keys.
     * @return List&lt;V&gt; array-reply list of elements.
     * @since 6.1
     */
    List<V> zunion(ZAggregateArgs aggregateArgs, K... keys);

    /**
     * Add multiple sorted sets and returns the resulting sorted set.
     *
     * @param aggregateArgs arguments to define aggregation and weights.
     * @param keys the keys.
     * @return List&lt;V&gt; array-reply list of scored values.
     * @since 6.1
     */
    List<ScoredValue<V>> zunionWithScores(ZAggregateArgs aggregateArgs, K... keys);

    /**
     * Add multiple sorted sets and returns the resulting sorted set.
     *
     * @param keys the keys.
     * @return List&lt;V&gt; array-reply list of scored values.
     * @since 6.1
     */
    List<ScoredValue<V>> zunionWithScores(K... keys);

    /**
     * Add multiple sorted sets and store the resulting sorted set in a new key.
     *
     * @param destination destination key.
     * @param keys source keys.
     * @return Long integer-reply the number of elements in the resulting sorted set at {@code destination}.
     */
    Long zunionstore(K destination, K... keys);

    /**
     * Add multiple sorted sets and store the resulting sorted set in a new key.
     *
     * @param destination the destination.
     * @param storeArgs arguments to define aggregation and weights.
     * @param keys the keys.
     * @return Long integer-reply the number of elements in the resulting sorted set at {@code destination}.
     */
    Long zunionstore(K destination, ZStoreArgs storeArgs, K... keys);

}
