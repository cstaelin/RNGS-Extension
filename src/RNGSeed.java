package org.nlogo.extensions.rngs;

import org.nlogo.api.Syntax;
import org.nlogo.api.Context;
import org.nlogo.api.Argument;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;

import java.util.Date;
import cern.jet.random.engine.MersenneTwister;

public class RNGSeed extends DefaultCommand {

  @Override
  public Syntax getSyntax() {
    int[] right = new int[]{Syntax.NumberType(), Syntax.NumberType()};
    return Syntax.commandSyntax(right);
  }

  @Override
  public void perform(Argument args[], Context context)
          throws ExtensionException, LogoException {

    int key = args[0].getIntValue();
    // get the seed first as a double to make sure it will fit in a 32-bit int.
    double trialSeed = args[1].getDoubleValue();
    if (trialSeed > Integer.MAX_VALUE || trialSeed < Integer.MIN_VALUE) {
      throw new ExtensionException("The seed to RNGSeed must be between "
              + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE + ".");
    }
    int seed = args[1].getIntValue();
    if (seed == 0) {
      RNGExtension.RNGs.put(key, new MersenneTwister(new Date()));
    } else {
      RNGExtension.RNGs.put(key, new MersenneTwister(seed));
    }
  }

  static public int getKey(Argument arg, Context context)
          throws ExtensionException, LogoException {
    // Checks to see if the key is in current HashMap.

    int key = arg.getIntValue();
    if (RNGExtension.RNGs.containsKey(key)) {
      return key;
    }
    throw new ExtensionException("No RNG has been initialized with this ID: " 
            + key);
  }
}

/*
 Copyright (c) 2007 David O'Sullivan
 Updated to NetLogo v.5.0 by Charles Staelin, March 2014.

 Packages cern.colt* , cern.jet*, cern.clhep 

 Copyright (c) 1999 CERN - European Organization for Nuclear Research. 
 Permission to use, copy, modify, distribute and sell this software 
 and its documentation for any purpose is hereby granted without fee, 
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
