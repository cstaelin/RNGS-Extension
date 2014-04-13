Random Number Generators

by Adam MacKenzie

Includes all of the core NetLogo probability distributions, as well as the Negative Binomial, Beta, Lognormal and von Mises distributions.

Broadens the scope of Dr. O'Sullivan's "Multiple Random Number Generator".

Usage is as follows:

    rngs:init 
clears all random number generators

    rngs:set-seed stream_id seed
creates a random number generator with identifier 
**stream_id** with a seed value (positive integer) of **seed**. Seeds of zero will be seeded from the system clock.

Probability distribution declarations are below. A random number generator must be created with the `rngs:set-seed` command prior to using any of the probability distributions below.

    rngs:rnd-float stream_id range 
returns a random number uniformly distributed over the interval (0, range)

    rngs:rnd-norm stream_id mean std-dev
returns a random number from the Normal distribution with the given mean and standard deviation.

    rngs:rnd-lognorm stream_id mean std-dev
	rngs:rnd-lognormN stream_id mean std-dev
both return a random number drawn from the Lognormal distribution.  `rnd-lognorm` expects the mean and standard deviation of the Lognormal distribution itself.  `rnd-lognormN` expects the mean and standard deviation of the simple Normal distribution from which the Lognormal distribution is constructed.

    rngs:rnd-poisson stream_id mean
returns a random number from the Poisson distribution with the given mean.

    rngs:rnd-gamma stream_id alpha lambda
returns a random number from the Gamma distribution with the given parameters.

    rngs:rnd-exponential stream_id mean
returns a random number from the Exponential distribution with the given mean.

    rngs:rnd-negbinomial stream trials p
returns a random number from the Negative Binomial distribution with the given number of trials and probability of **success**.

    rngs:rnd-beta stream_id alpha beta
returns a random number from the Beta distribution with the given parameters.

    rngs:rnd-vonmises stream_id k
returns a random number from the von Mises distribution with the given parameter.

Updated to NetLogo 5.0 and augmented with the Lognormal, Poisson and von Mises distributions by Charles Staelin, March 2014. 