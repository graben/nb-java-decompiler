/*
 * Copyright (C) 2021 Moacir da Roza Flores <moacirrf@gmail.com>
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
package io.github.moacirrf.javadecompiler;

import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 *
 * @author Moacir da Roza Flores <moacirrf@gmail.com>
 */
public final class DecompilerFactory {

    public static Decompiler create() {
        Preferences prefs = NbPreferences.forModule(DecompilerFactory.class);
        String decompiler = prefs.get("io.github.moacirrf.javadecompiler.type", "cfr");
        DecompilerType type = DecompilerType.valueOf(decompiler.toUpperCase());
        switch (type) {
            case CFR:
                return new io.github.moacirrf.javadecompiler.cfr.DecompilerClassImpl();
            case VINEFLOWER:
                return new io.github.moacirrf.javadecompiler.vineflower.DecompilerClassImpl();
            default:
                throw new IllegalArgumentException();
        }
    }

    private DecompilerFactory() {
    }

    private enum DecompilerType {
        CFR, VINEFLOWER;
    }
}
