/**
 * Copyright (C) 2015 Johannes Schnatterer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.schnatterer.java.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Implementation of basic set (in a mathematical sense) operations.
 * 
 * @author schnatterer
 *
 */
public class Sets {

	/**
	 * Returns the intersection of two sets, as stream. The streams might have different types. The result might contain
	 * duplicates.
	 * 
	 * @param a
	 *            set a, of type <code>T</code>
	 * @param b
	 *            set b, of <b>other</b> type <code>S</code>. Recommendation: Use a {@link HashSet} here, as a lot of
	 *            {@link Collection#contains(Object)} is called
	 * @param convertAtoB
	 *            converts elements of <code>a</code> to be comparable with type <code>b</code>
	 * @param <T>
	 *            type of set <code>a</code>
	 * @param <S>
	 *            type of set <code>b</code>
	 * @return a new instance that contains the intersection between <code>a</code> and b<code>b</code>
	 */
	public static <T, S> Stream<T> intersection(Collection<T> a, Collection<S> b, Function<T, S> convertAtoB) {
		return a.stream().filter(aMember -> b.contains(convertAtoB.apply(aMember)));
	}

	/**
	 * Returns the relative complement of b in a ("a without b"). The streams might have different types. The result
	 * might contain duplicates.
	 * 
	 * @param a
	 *            set a, of type <code>T</code>
	 * @param b
	 *            set b, of <b>other</b> type <code>S</code>. Recommendation: Use a {@link HashSet} here, as a lot of
	 *            {@link Collection#contains(Object)} is called
	 * @param convertAtoB
	 *            converts elements of <code>a</code> to be comparable with type <code>b</code>
	 * @param <T>
	 *            type of set <code>a</code> (the one <code>b</code> is removed from)
	 * @param <S>
	 *            type of set <code>b</code> (the one that is removed from <code>a</code> )
	 * 
	 * @return a new instance that contains the elements of <code>a</code> that are not in b<code>b</code>
	 */
	public static <T, S> Stream<T> relativeComplement(Collection<T> a, Collection<S> b, Function<T, S> convertAtoB) {
		return a.stream().filter(aMember -> !b.contains(convertAtoB.apply(aMember)));
	}
}
