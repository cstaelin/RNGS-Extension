package org.nlogo.extensions.rngs;

import org.nlogo.api.Syntax;
import org.nlogo.api.Context;
import org.nlogo.api.Argument;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;

import cern.jet.random.Normal;

public class RandomLogNormal extends DefaultReporter {
  //Use:  rngs:rnd-norm <stream-id> <mean> <std-dev>

  static final int USE_NORMAL_PARAMS = 0;
  static final int USE_LOGNORMAL_PARAMS = 1;
  private final int useParam;
  
  public RandomLogNormal( int paramType ) {
    useParam = paramType;
}
  
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
    /* mean and stdv are the mean and standard deviation for the underlying
     * normal distribution from which the lognormal distribution is being 
     * generated. They either have been passed directly to the reporter when
     * useParam = USE_NORMAL_PARAMS, or are calculated from the parameters
     * of the lognormal distribution which have been passed when 
     * useParam = USE_LOGNORMAL_PARAMS.
     * 
     * The conversion of the mean and standard deviation of the lognormal 
     * distribution are converted to the partameters of the underlying 
     * normal distribution using the method described in Railsback & Grimm, 
     * p. 207, which is equivalent to, but less comutationally intensive 
     * than the standard formulas found (say) in Wikipedia.
     */
    double mean;
    double stdv;
    
    int key = RNGSeed.getKey(args[0], context);
    double m = args[1].getDoubleValue();
    double s = args[2].getDoubleValue();
    if (useParam == USE_NORMAL_PARAMS) {
      mean = m;
      stdv = s;
    } else {
      double v = Math.log(1.0 + ((s * s) / (m * m)));
      stdv = Math.sqrt(v);
      mean = Math.log(m) - (v / 2);
    }
    
    Normal nm = new Normal(mean, stdv, RNGExtension.RNGs.get(key));
    return Math.exp(nm.nextDouble());
  }
}

/*
 Copyright (c) 2009 Adam MacKenzie
 Portions of code Copyright (c) 2007 David O'Sullivan
 Updated to NetLogo v.5.0 by Charles Staelin, March 2014.
 This class added by Charles Staelin.

 Packages cern.colt* , cern.jet*, cern.clhep 

 Copyright (c) 1999 CERN - European Organization for Nuclear Research. 
 Permission to use, copy, modify, distribute and sell this software 
 and its documentation for any purpose is hereby granted without 
 fee, provided that the above copyright notice appear in all copies 
 and that both that copyright notice and this permission notice 
 appear in supporting documentation. CERN makes no representations 
 about the suitability of this software for any purpose. It is 
 provided "as is" without expressed or implied warranty.

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
