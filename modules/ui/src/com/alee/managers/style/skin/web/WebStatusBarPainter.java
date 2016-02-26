/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.managers.style.skin.web;

import com.alee.extended.statusbar.IStatusBarPainter;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusBarUI;
import com.alee.managers.style.skin.web.data.decoration.IDecoration;

/**
 * Web-style painter for WebStatusBar component.
 * It is used as WebStatusBarUI default painter.
 *
 * @param <E> component type
 * @param <U> component UI type
 * @param <D> decoration type
 * @author Mikle Garin
 */

public class WebStatusBarPainter<E extends WebStatusBar, U extends WebStatusBarUI, D extends IDecoration<E, D>>
        extends AbstractContainerPainter<E, U, D> implements IStatusBarPainter<E, U>
{
}