/**
 * The LGPL License
 * <p>
 * Copyright (C) 2019-2025 CheatBreaker.net
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.cheatbreaker.general.utils;

import lombok.SneakyThrows;

import java.util.concurrent.ForkJoinPool;

public class AsyncThreadExecutor {
    public static final ForkJoinPool common = ForkJoinPool.commonPool();

    @SneakyThrows
    public static void runLater(Runnable consumer, long delay) {
        common.wait(delay);
        common.execute(consumer);
    }

    @SneakyThrows
    public static void run(Runnable consumer) {
        common.execute(consumer);
    }
}