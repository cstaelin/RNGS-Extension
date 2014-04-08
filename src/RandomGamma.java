package org.nlogo.extensions.rngs;

import org.nlogo.api.Syntax;
import org.nlogo.api.Context;
import org.nlogo.api.Argument;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;

import cern.jet.random.Gamma;

public class RandomGamma extends DefaultReporter {

  //Use:  rngs:rnd-gamma <stream-id> <alpha> <lambda>
  @Override
  public Syntax getSyntax() {
    int[] right = new int[]{Syntax.NumberType(), Syntax.NumberType(),
      Syntax.NumberType()};
    int ret = Syntax.NumberType();
    return Syntax.reporterSyntax(right, ret);
  }

  @Override
  public Object report(Argument args[], Context context)
          throws ExtensionException, LogoException {

    int key = RNGSeed.getKey(args[0], context);
    double alpha = args[1].getDoubleValue();
    double lambda = args[2].getDoubleValue();

    try {
      Gamma dist = new Gamma(alpha, lambda, RNGExtension.RNGs.get(key));
      return dist.nextDouble();
    } catch (IllegalArgumentException e) {
      throw new ExtensionException("Illegal arguments to Gamma. "
              + e.toString());
    }
  }
}

/*
 Copyright (c) 2009 Adam MacKenzie
 Portions of code Copyright (c) 2007 David O'Sullivan
 Updated to NetLogo v.5.0 by Charles Staelin, March 2014.

 Packages cern.colt* , cern.jet*, cern.clhep 

 Copyright (c) 1999 CERN - European Organization for Nuclear Research. 
 Permission to use, copy, modify, distribute and sell this software and 
 its documentation for any purpose is hereby granted without fee, 
 provided that the above copyright notice appear in all copies and 
 that both that copyright notice and this permission notice appear 
 in supporting documentation. CERN makes no representations about 
 the suitability of this software for any purpose. It is provided 
 "as is" without expressed or implied warranty.


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
