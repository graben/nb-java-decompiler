/*
 * Copyright (C) 2023 Benjamin Graf
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.moacirrf.javadecompiler.vineflower;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jetbrains.java.decompiler.main.extern.IContextSource;
import org.jetbrains.java.decompiler.main.extern.IResultSaver;
import org.openide.filesystems.FileObject;

import io.github.moacirrf.javadecompiler.files.FileSystemHelper;

public class SimpleContextSource implements IContextSource {

    private final FileObject fileObject;
    private final String className;

    public SimpleContextSource(FileObject fileObject) {
        this.fileObject = fileObject;
        this.className = FileSystemHelper.extractName(fileObject);
    }

    @Override
    public String getName() {
        return className + CLASS_SUFFIX;
    }

    @Override
    public Entries getEntries() {
        return new Entries(List.of(Entry.atBase(className)), List.of(), List.of());
    }

    @Override
    public InputStream getInputStream(String resource) throws IOException {
        return fileObject.getInputStream();
    }

    @Override
    public IOutputSink createOutputSink(IResultSaver saver) {
        return new SimpleOutputSink(saver);
    }

    private static class SimpleOutputSink implements IOutputSink {

        private final IResultSaver saver;

        public SimpleOutputSink(IResultSaver saver) {
            this.saver = saver;
        }

        @Override
        public void close() throws IOException {
            // noop
        }

        @Override
        public void begin() {
            // noop
        }

        @Override
        public void acceptOther(String path) {
            // noop
        }

        @Override
        public void acceptDirectory(String directory) {
            // noop
        }

        @Override
        public void acceptClass(String qualifiedName, String fileName, String content, int[] mapping) {
            saver.saveClassFile(null, null, null, content, null);
        }
    }
}
