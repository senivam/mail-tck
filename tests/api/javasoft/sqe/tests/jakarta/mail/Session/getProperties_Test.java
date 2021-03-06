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

package javasoft.sqe.tests.jakarta.mail.Session;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getProperties()</strong> API.
 * It this by returning the Properties object and we confirm its type. <p>
 *
 *		Returns the Properties object associated with this Session. <p>
 * api2test: public Properties getProperties() <p>
 *
 * how2test: Test it with different Sessions. Test passes if the object <p>
 *	     type returned is Properties.
 */

public class getProperties_Test extends MailTest {

    public static void main( String argv[] )
    {
        getProperties_Test test = new getProperties_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Session: getProperties()\n");

        try {
             // Create a Properties object
             Properties props = new Properties();
	     if (host != null)
		 props.put("mail."+ protocol +".host", host);
	     if (user != null)
		 props.put("mail."+ protocol +".user", user);
	     props.put("mail."+ protocol +".tester", "tester");
	     props.put("mail.store.protocol", protocol);

	     // Get a Session object
	     Session session_1 = Session.getInstance(props, null);

	  // BEGIN UNIT TEST 1:
             out.println("UNIT TEST 1: getProperties()");
             Properties prop_1 = session_1.getProperties();     // API TEST

             if ( prop_1 != null ) {
                   out.println("\nmail.protocol.host = " + prop_1.getProperty("mail."+ protocol +".host"));
		   out.println("mail.protocol.user = " + prop_1.getProperty("mail."+ protocol +".user"));
		   out.println("mail.protocol.tester = " + prop_1.getProperty("mail."+ protocol +".tester"));
		   out.println("mail.store.protocol = " + prop_1.getProperty("mail.store.protocol"));
                   out.println("UNIT TEST 1:  passed\n");
             } else {
                     out.println("UNIT TEST 1:  FAILED\n");
                     errors++;
             }
          // END UNIT TEST 1:
          // BEGIN UNIT TEST 2:
	     // Get a Session object
	     Session session_2 = Session.getInstance(properties, null);

             out.println("UNIT TEST 2: getProperties()");
             Properties prop_2 = session_2.getProperties();     // API TEST

             if ( prop_2 != null ) {
                   out.println("\nmail.protocol.host = " + prop_2.getProperty("mail."+ protocol +".host"));
                   out.println("mail.protocol.user = " + prop_2.getProperty("mail."+ protocol +".user"));
                   out.println("mail.store.protocol = " + prop_2.getProperty("mail.store.protocol"));
                   out.println("UNIT TEST 2:  passed\n");
             } else {
                     out.println("UNIT TEST 2:  FAILED\n");
                     errors++;
             }
          // END UNIT TEST 2:
             checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
