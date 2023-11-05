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

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jetbrains.java.decompiler.main.Fernflower;
import org.jetbrains.java.decompiler.main.extern.IFernflowerPreferences;
import org.jetbrains.java.decompiler.util.JrtFinder;
import org.openide.filesystems.FileObject;

import io.github.moacirrf.javadecompiler.Decompiler;
import io.github.moacirrf.javadecompiler.ExceptionHandler;
import io.github.moacirrf.javadecompiler.files.FileSystemHelper;

import static com.machinezoo.noexception.Exceptions.wrap;

public class DecompilerClassImpl implements Decompiler<String, FileObject> {

    @Override
    public String decompile(FileObject file) {
        return wrap(ExceptionHandler::handleException).get(() -> {
            Map<String, Object> customProperties = Map.of(IFernflowerPreferences.INCLUDE_JAVA_RUNTIME, JrtFinder.CURRENT);
            SimpleResultSaver saver = new SimpleResultSaver();
            Fernflower engine = new Fernflower(saver, customProperties, new JulLogger());
            for (FileObject classFile : findClassFiles(file)) {
                engine.addSource(new SimpleContextSource(classFile));
            }
            engine.decompileContext();
            return saver.getContent();
        });
    }

    private List<FileObject> findClassFiles(FileObject file) {
        List<FileObject> result = new LinkedList<>();
        String className = FileSystemHelper.extractName(file);
        FileSystemHelper helper = FileSystemHelper.of(file);
        FileObject classFile = helper.findResource(className);
        result.add(classFile);
        Enumeration<? extends FileObject> enumeration = classFile.getParent().getData(false);
        while (enumeration.hasMoreElements()) {
            FileObject element = enumeration.nextElement();
            if (FileSystemHelper.extractName(element).startsWith(className + "$")) {
                result.add(element);
            }
        }
        return result;
    }
}
