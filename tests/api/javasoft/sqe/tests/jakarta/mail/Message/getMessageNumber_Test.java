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

package javasoft.sqe.tests.jakarta.mail.Message;

import java.util.*;
import java.io.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.mail.util.MailTest;

/**
 * This class tests the <strong>getMessageNumber()</strong> API.
 * It does this by invoking the test api and then checking
 * the type of the returned object.	<p>
 *
 *		Get the Message number for this Message.<p>
 * api2test: public int getMessageNumber()  <p>
 *
 * how2test: Call this API for any message and verify that it returns an integers number.
 *	     If this operation is successfull then this testcase passes, otherwise it fails.<p>
 *
 *	     A Message object's message number is the relative position <p>
 *	     of this Message in its Folder. A derived or newly composed
 *	     message has 0 as their message number.
 */

public class getMessageNumber_Test extends MailTest {

    public static void main( String argv[] )
    {
        getMessageNumber_Test test = new getMessageNumber_Test();
        Status s = test.run(argv, System.err, System.out);
	s.exit();
    }

    public Status run(String argv[], PrintWriter log, PrintWriter out)
    {
	super.run(argv, log, out);
	parseArgs(argv);	// parse command-line options

        out.println("\nTesting class Message: getMessageNumber()\n");

        try {
          // Connect to host server
             Store store = connect2host(protocol, host, user, password);

          // Get a Folder object
	     Folder root = getRootFolder(store);
             Folder folder = root.getFolder(mailbox);

             if ( folder == null ) {
                  return Status.failed("Invalid folder object!");
             }
             folder.open(Folder.READ_ONLY);

	     if( msgcount == -1 ) {
                 msgcount = folder.getMessageCount();
                 if( msgcount < 1 )
                     return Status.failed("Mail folder is empty!");
             }
	     int msgid;

             for( int i = 1; i <= msgcount; i++ )
             {
            // Get the message
               MimeMessage msg = (MimeMessage)folder.getMessage(i);

	       if( msg == null ) {
		   log.println("WARNING: FAILED TO GET MESSAGE NUMBER: "+ i);
		   continue;
	       }
	    // BEGIN UNIT TEST:
	       // Get message number
	       out.println("UNIT TEST "+ i +":  getMessageNumber()");

	       msgid = msg.getMessageNumber();	// API TEST

	       if( msgid == i )
                   out.println("UNIT TEST "+ i +":  passed\n");
	       else {
		     out.println("UNIT TEST "+ i +":  FAILED\n");
		     errors++;
	       }
	    // END UNIT TEST:
	     }
	     folder.close(false);
	     store.close();
	     checkStatus();

        } catch ( Exception e ) {
	     handlException(e);
        }
	return status;
     }
}
