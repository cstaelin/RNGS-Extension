/*
 * Created on 19/10/2006
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.nlogo.extensions.rngs;

import org.nlogo.api.Syntax;
import org.nlogo.api.Context;
import org.nlogo.api.Argument;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;

public class InitialiseCollection extends DefaultCommand {

  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax();
  }

  @Override
  public void perform(Argument args[], Context context) throws ExtensionException {
    try {
      RNGExtension.RNGs.clear();
    } catch (Exception e) {
      throw new ExtensionException("Something went wrong in InitialiseCollection: "
              + e.toString());
    }
  }
}


/*
Copyright (c) 2007 David O'Sullivan
Updated to NetLogo v.5.0 by Charles Staelin, March 2014.

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/
