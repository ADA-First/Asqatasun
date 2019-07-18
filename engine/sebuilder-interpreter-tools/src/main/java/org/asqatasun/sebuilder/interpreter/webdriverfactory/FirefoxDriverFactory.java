/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2019  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.sebuilder.interpreter.webdriverfactory;

import com.google.common.base.Preconditions;
import com.sebuilder.interpreter.webdriverfactory.WebDriverFactory;
import java.util.HashMap;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxDriverFactory implements WebDriverFactory {

    private static final String DISPLAY_PROPERTY = "display";
    
    FirefoxProfile firefoxProfile;
    public void setFirefoxProfile(FirefoxProfile firefoxProfile) {
        this.firefoxProfile = firefoxProfile;
    }
    
    int screenWidth=-1;
    public void setScreenWidht(int screenWidth) {
        this.screenWidth = screenWidth;
    }
    
    int screenHeight=-1;
    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }
    /**
     * 
     * @param config
     * @return A FirefoxDriver.
     */
    @Override
    public RemoteWebDriver make(HashMap<String, String> config) {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(false);
        options.setBinary(new FirefoxBinary());
        RemoteWebDriver remoteWebDriver = new FirefoxDriver(options);

        if (screenHeight != -1 && screenWidth!= -1) {
            remoteWebDriver.manage().window().setSize(new Dimension(screenWidth, screenHeight));
        }
        return remoteWebDriver;
    }
}
