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

import java.util.jar.Manifest;

import org.jetbrains.java.decompiler.main.extern.IResultSaver;

public class SimpleResultSaver implements IResultSaver {

    private String content;

    public String getContent() {
        return content;
    }

    @Override
    public void saveFolder(String path) {
        // noop
    }

    @Override
    public void copyFile(String source, String path, String entryName) {
        // noop
    }

    @Override
    public void saveClassFile(String path, String qualifiedName, String entryName, String content, int[] mapping) {
        this.content = content;
    }

    @Override
    public void createArchive(String path, String archiveName, Manifest manifest) {
        // noop
    }

    @Override
    public void saveDirEntry(String path, String archiveName, String entryName) {
        // noop
    }

    @Override
    public void copyEntry(String source, String path, String archiveName, String entry) {
        // noop
    }

    @Override
    public void saveClassEntry(String path, String archiveName, String qualifiedName, String entryName, String content) {
        // noop
    }

    @Override
    public void closeArchive(String path, String archiveName) {
        // noop
    }
}
