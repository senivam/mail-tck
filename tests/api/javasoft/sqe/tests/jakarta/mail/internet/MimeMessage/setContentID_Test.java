/*
 * Copyright (c) 2002, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package javasoft.sqe.tests.jakarta.mail.internet.MimeMessage;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>setContentID()</strong> API.
 * It does by passing various valid input values and then checking
 * the type of the returned object.	<p>
 *
 *		Set the "Content-ID" header field of Message. <p>
 * api2test: public void setContentID(String)  <p>
 *
 * how2test: Call API, then check by calling getContentID(), if both the get and
 *	     set values are the same then testcase passes, otherwise it fails.
 */

public class setContentID_Test extends MailTest {

    public static void main( String argv[] )
    {
        setContentID_Test test = new setContentID_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class MimeMessage: setContentID(String)\n");

        try {
          // Create a MimeMessage object
             Session session = Session.getInstance(properties, null);
             MimeMessage msg = new MimeMessage(session);

             if( msg == null ) {
                 log.println("WARNING: FAILED TO CREATE MESSAGE OBJECT");
                 return Status.failed("Failed to create Message object");
             }
          // BEGIN UNIT TEST:
	     String strvalue = "cool hand luke";
             out.println("UNIT TEST 1: setContentID("+strvalue+")");

             msg.setContentID(strvalue);	// API TEST

	     if( msg.getContentID() != null ) {
                 if ( strvalue.equals( msg.getContentID() ))
                      out.println("UNIT TEST 1: passed\n");
                 else {
                       out.println("UNIT TEST 1: FAILED\n");
                       errors++;
                 }
	     } else
		   out.println("Warning: Null object returned by getContentID()");

	     strvalue = "";
             out.println("UNIT TEST 2: setContentID("+strvalue+")");

             msg.setContentID(strvalue);        // API TEST

             if( msg.getContentID() != null ) {
                 if ( strvalue.equals( msg.getContentID() ))
                      out.println("UNIT TEST 2: passed\n");
                 else {
                       out.println("UNIT TEST 2: FAILED\n");
                       errors++;
                 }
             } else
                   out.println("Warning: Null object returned by getContentID()");

          // END UNIT TEST:

             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
