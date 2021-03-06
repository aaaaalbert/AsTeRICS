
/*
 *    AsTeRICS - Assistive Technology Rapid Integration and Construction Set
 * 
 * 
 *        d8888      88888888888       8888888b.  8888888 .d8888b.   .d8888b. 
 *       d88888          888           888   Y88b   888  d88P  Y88b d88P  Y88b
 *      d88P888          888           888    888   888  888    888 Y88b.     
 *     d88P 888 .d8888b  888   .d88b.  888   d88P   888  888         "Y888b.  
 *    d88P  888 88K      888  d8P  Y8b 8888888P"    888  888            "Y88b.
 *   d88P   888 "Y8888b. 888  88888888 888 T88b     888  888    888       "888
 *  d8888888888      X88 888  Y8b.     888  T88b    888  Y88b  d88P Y88b  d88P
 * d88P     888  88888P' 888   "Y8888  888   T88b 8888888 "Y8888P"   "Y8888P" 
 *
 *
 *                    homepage: http://www.asterics.org 
 *
 *     This project has been partly funded by the European Commission, 
 *                      Grant Agreement Number 247730
 *  
 *  
 *    License: GPL v3.0 (GNU General Public License Version 3.0)
 *                 http://www.gnu.org/licenses/gpl.html
 * 
 */
 
/**
 * @file signal_processing.h
 *
 * @author Javier Acedo
 * @date 20/08/2010
 * @version 1.0
 *
 * @brief Simple signal processing functions
 *
 * This file contains the definition of the simple signal processing functions
 * used in the AsTeRICS project
 *
 **/
#ifndef COMPUTE_BAND_POWER_H_
#define COMPUTE_BAND_POWER_H_

#ifndef BOOL
typedef unsigned char BOOL;
#endif
#ifndef NULL
#define NULL	0x00000000
#endif


/*
 * CBP functions
 */

/**
 * Initializes the sin/cos tables
 **/
void CBP_initialization ();

/**
 * Real Discrete Fourier Transformation calculation
 * \param [in] samples
 *		Samples over which the FFT is going to be calculated
 * \param [in] len
 *		Length of the samples. It must be a power of 2 with a Maximum of 8192
 * \param [out] samples
 *		The Real and Imaginary results of the FFT \n
 *				samples[2*k] = R[k], 0<=k<n/2 \n
 *				samples[2*k+1] = I[k], 0<k<n/2 \n
 *				samples[1] = R[n/2] \n
 * \param [out] output
 *		The module of the results. Its length is len/2
 **/
void CBP_implementation (double * samples,
                         int len,
 						 double * output);


#endif /*COMPUTE_BAND_POWER_H_*/
