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

package com.alee.laf.desktoppane;

import com.alee.managers.style.*;
import com.alee.painter.Painter;
import com.alee.painter.PainterSupport;
import com.alee.utils.SwingUtils;
import com.alee.utils.swing.DataRunnable;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;

/**
 * Custom UI for JInternalFrame component.
 *
 * @author Mikle Garin
 */

public class WebInternalFrameUI extends BasicInternalFrameUI implements Styleable, ShapeProvider, MarginSupport, PaddingSupport
{
    /**
     * Component painter.
     */
    protected IInternalFramePainter painter;

    /**
     * Runtime variables.
     */
    protected Insets margin = null;
    protected Insets padding = null;

    /**
     * Returns an instance of the WebInternalFrameUI for the specified component.
     * This tricky method is used by UIManager to create component UIs when needed.
     *
     * @param c component that will use UI instance
     * @return instance of the WebInternalFrameUI
     */
    public static ComponentUI createUI ( final JComponent c )
    {
        return new WebInternalFrameUI ( ( JInternalFrame ) c );
    }

    /**
     * Constructs new internal frame UI.
     *
     * @param b internal frame to which this UI will be applied
     */
    public WebInternalFrameUI ( final JInternalFrame b )
    {
        super ( b );
    }

    /**
     * Installs UI in the specified component.
     *
     * @param c component for this UI
     */
    @Override
    public void installUI ( final JComponent c )
    {
        super.installUI ( c );

        // Installing title pane
        if ( northPane instanceof WebInternalFrameTitlePane )
        {
            ( ( WebInternalFrameTitlePane ) northPane ).install ();
        }

        // Applying skin
        StyleManager.installSkin ( frame );
    }

    /**
     * Uninstalls UI from the specified component.
     *
     * @param c component with this UI
     */
    @Override
    public void uninstallUI ( final JComponent c )
    {
        // Uninstalling applied skin
        StyleManager.uninstallSkin ( frame );

        // Uninstalling title pane
        if ( northPane instanceof WebInternalFrameTitlePane )
        {
            ( ( WebInternalFrameTitlePane ) northPane ).uninstall ();
        }

        super.uninstallUI ( c );
    }

    @Override
    public StyleId getStyleId ()
    {
        return StyleManager.getStyleId ( frame );
    }

    @Override
    public StyleId setStyleId ( final StyleId id )
    {
        return StyleManager.setStyleId ( frame, id );
    }

    @Override
    public Shape provideShape ()
    {
        return PainterSupport.getShape ( frame, painter );
    }

    @Override
    public Insets getMargin ()
    {
        return margin;
    }

    @Override
    public void setMargin ( final Insets margin )
    {
        this.margin = margin;
        PainterSupport.updateBorder ( getPainter () );
    }

    @Override
    public Insets getPadding ()
    {
        return padding;
    }

    @Override
    public void setPadding ( final Insets padding )
    {
        this.padding = padding;
        PainterSupport.updateBorder ( getPainter () );
    }

    /**
     * Returns internal frame painter.
     *
     * @return internal frame painter
     */
    public Painter getPainter ()
    {
        return PainterSupport.getAdaptedPainter ( painter );
    }

    /**
     * Sets internal frame painter.
     * Pass null to remove internal frame painter.
     *
     * @param painter new internal frame painter
     */
    public void setPainter ( final Painter painter )
    {
        PainterSupport.setPainter ( frame, new DataRunnable<IInternalFramePainter> ()
        {
            @Override
            public void run ( final IInternalFramePainter newPainter )
            {
                WebInternalFrameUI.this.painter = newPainter;
            }
        }, this.painter, painter, IInternalFramePainter.class, AdaptiveInternalFramePainter.class );
    }

    /**
     * Creates and returns internal pane north panel.
     *
     * @param frame internal frame
     * @return north panel for specified internal frame
     */
    @Override
    protected JComponent createNorthPane ( final JInternalFrame frame )
    {
        return new WebInternalFrameTitlePane ( frame, frame );
    }

    /**
     * Paints internal frame.
     *
     * @param g graphics
     * @param c component
     */
    @Override
    public void paint ( final Graphics g, final JComponent c )
    {
        if ( painter != null )
        {
            painter.paint ( ( Graphics2D ) g, SwingUtils.size ( c ), c, this );
        }
    }

    @Override
    public Dimension getPreferredSize ( final JComponent c )
    {
        return PainterSupport.getPreferredSize ( c, super.getPreferredSize ( c ), painter );
    }
}