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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger;

import static org.jetbrains.java.decompiler.main.extern.IFernflowerLogger.Severity.ERROR;
import static org.jetbrains.java.decompiler.main.extern.IFernflowerLogger.Severity.INFO;
import static org.jetbrains.java.decompiler.main.extern.IFernflowerLogger.Severity.TRACE;
import static org.jetbrains.java.decompiler.main.extern.IFernflowerLogger.Severity.WARN;

public class JulLogger extends IFernflowerLogger {

    private static final Logger LOGGER = Logger.getLogger(JulLogger.class.getName());

    @Override
    public void writeMessage(String message, Severity severity) {
        writeMessage(message, severity, null);
    }

    @Override
    public void writeMessage(String message, Severity severity, Throwable t) {
        if (accepts(severity)) {
            Level level = null;
            switch (severity) {
                case TRACE:
                    level = Level.FINEST;
                    break;
                case INFO:
                    level = Level.INFO;
                    break;
                case WARN:
                    level = Level.WARNING;
                    break;
                case ERROR:
                    level = Level.SEVERE;
                    break;
            }
            if (t == null) {
                LOGGER.log(level, message);
            } else {
                LOGGER.log(level, message, t);
            }
        }
    }

    @Override
    public void startProcessingClass(String className) {
        if (accepts(Severity.INFO)) {
            writeMessage("Preprocessing class " + className, Severity.INFO);
        }
    }

    @Override
    public void endProcessingClass() {
        if (accepts(Severity.INFO)) {
            writeMessage("... done", Severity.INFO);
        }
    }

    @Override
    public void startReadingClass(String className) {
        if (accepts(Severity.INFO)) {
            writeMessage("Decompiling class " + className, Severity.INFO);
        }
    }

    @Override
    public void endReadingClass() {
        if (accepts(Severity.INFO)) {
            writeMessage("... done", Severity.INFO);
        }
    }

    @Override
    public void startClass(String className) {
        if (accepts(Severity.INFO)) {
            writeMessage("Processing class " + className, Severity.TRACE);
        }
    }

    @Override
    public void endClass() {
        if (accepts(Severity.INFO)) {
            writeMessage("... proceeded", Severity.TRACE);
        }
    }

    @Override
    public void startMethod(String methodName) {
        if (accepts(Severity.INFO)) {
            writeMessage("Processing method " + methodName, Severity.TRACE);
        }
    }

    @Override
    public void endMethod() {
        if (accepts(Severity.INFO)) {
            writeMessage("... proceeded", Severity.TRACE);
        }
    }

    @Override
    public void startWriteClass(String className) {
        if (accepts(Severity.INFO)) {
            writeMessage("Writing class " + className, Severity.TRACE);
        }
    }

    @Override
    public void endWriteClass() {
        if (accepts(Severity.INFO)) {
            writeMessage("... written", Severity.TRACE);
        }
    }
}
