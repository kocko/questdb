/*******************************************************************************
 *     ___                  _   ____  ____
 *    / _ \ _   _  ___  ___| |_|  _ \| __ )
 *   | | | | | | |/ _ \/ __| __| | | |  _ \
 *   | |_| | |_| |  __/\__ \ |_| |_| | |_) |
 *    \__\_\\__,_|\___||___/\__|____/|____/
 *
 *  Copyright (c) 2014-2019 Appsicle
 *  Copyright (c) 2019-2022 QuestDB
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 ******************************************************************************/

package io.questdb.tasks;

import io.questdb.cairo.sql.Function;
import io.questdb.cairo.sql.PageAddressCache;
import io.questdb.cairo.sql.SymbolTableSource;
import io.questdb.std.DirectLongList;

public class PageFrameTask {
    private DirectLongList rows;
    private Function filter;
    private long producerId;
    private PageAddressCache pageAddressCache;
    private int frameIndex;
    private int frameCount;
    private long frameRowCount;
    private SymbolTableSource symbolTableSource;
    private boolean failed;

    public Function getFilter() {
        return filter;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public long getFrameRowCount() {
        return frameRowCount;
    }

    public PageAddressCache getPageAddressCache() {
        return pageAddressCache;
    }

    public long getProducerId() {
        return producerId;
    }

    public DirectLongList getRows() {
        return rows;
    }

    public SymbolTableSource getSymbolTableSource() {
        return symbolTableSource;
    }

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public void of(
            long producerId,
            PageAddressCache cache,
            int frameIndex,
            int frameCount,
            long rowCount,
            SymbolTableSource symbolTableSource,
            Function filter,
            DirectLongList rows
    ) {
        this.producerId = producerId;
        this.pageAddressCache = cache;
        this.frameIndex = frameIndex;
        this.frameCount = frameCount;
        this.frameRowCount = rowCount;
        this.symbolTableSource = symbolTableSource;
        this.filter = filter;
        this.rows = rows;
        this.failed = false;
    }

    public DirectLongList takeRows() {
        DirectLongList dll = rows;
        rows = null;
        return dll;
    }
}
