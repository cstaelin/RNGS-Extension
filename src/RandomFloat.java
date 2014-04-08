package org.nlogo.extensions.rngs;

import org.nlogo.api.Syntax;
import org.nlogo.api.Context;
import org.nlogo.api.Argument;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;

public class RandomFloat extends DefaultReporter {
  
  @Override
  public Syntax getSyntax() {
    int[] right = new int[]{Syntax.NumberType(), Syntax.NumberType()};
    int ret = Syntax.NumberType();
    return Syntax.reporterSyntax(right, ret);
  }
  
  @Override
  public Object report(Argument args[], Context context)
          throws ExtensionException, LogoException {
    
    int key = RNGSeed.getKey(args[0], context);
    double range = args[1].getDoubleValue();
    
    return RNGExtension.RNGs.get(key).nextFloat() * range;
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
