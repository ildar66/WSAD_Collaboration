/************************************************************************
 * Licensed Materials - Property of IBM
 * (c) Copyright IBM Corp. 2003.  All rights reserved.
 *
 * US Government Users Restricted Rights - Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM
 * Corp.
 *
 * DISCLAIMER OF WARRANTIES.  The following [enclosed] code is
 * sample code created by IBM Corporation.  This sample code is
 * not part of any standard or IBM product and is provided to you
 * solely for the purpose of assisting you in the development of
 * your applications.  The code is provided "AS IS", without
 * warranty of any kind.  IBM shall not be liable for any damages
 * arising out of your use of the sample code, even if they have
 * been advised of the possibility of such damages.
 *************************************************************************/

package com.ibm.odcb.tutorial;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.ibm.odcb.tutorial.businessobjects.Portfolio;
import com.ibm.odcb.tutorial.businessobjects.Position;
import com.ibm.odcb.tutorial.businessobjects.Root;
import com.ibm.odcb.tutorial.businessobjects.Stock;
import com.ibm.odcb.tutorial.businessobjects.User;
import com.ibm.odcb.jrender.emitters.WDO4JSEmitter;
import com.ibm.odcb.jrender.mediators.PageContext;
import com.ibm.odcb.jrender.misc.ObjectSerializer;
import com.ibm.odcb.jrender.misc.Streamer;

/**
 * @author ldh
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class WDO4JSEmitterTest {
	public static final String COPYRIGHT = com.ibm.odcb.utilities.IBMCopyright.SHORT_COPYRIGHT;

	public static void main(String[] args) {
		Streamer.ForceStdErr();
		Streamer.StartThread();
		Streamer.setLogLevel(3);

		Streamer
			.status
			.Header()
			.println("WDO4JSEmitterTest program to be called with a single parameter")
			.Header()
			.println("  - <output directory>")
			.println();

		if (args.length != 1) {
			Streamer.error.Header().println("Program invoked with incorrect parameters");
			return;
		}

		try {
			// Get the source data
			Root R = getSampleDataset();
			// Create a file PrintWriter to write the result to
			PrintWriter Out = new PrintWriter(new FileOutputStream(args[0] + "\\Test1.js"));
			// Creating a Page context
			PageContext Ctx = new PageContext();
			// Creating the emitter
			WDO4JSEmitter E = new WDO4JSEmitter();
			// Initializing the emitter to output an instance of the Root class, using the class' type name
			// to locate the right EClassMap, with no qualifier, outputting the model in JavaScript, but the
			// data in XMI, and using 'UserPortfolios' as the name of the datastructue in the browser
			E.Init(R, R.getClass().getName(), null, true, true, "UserPortfolios");
			// Exporting the data in the stream
			E.Export(Out, Ctx);
			// Counting how many "records" were output
			//System.out.println(Ctx.NextInstanceCount() + " records output in " + args[0] + "\\Test1.js");
			// Getting the Id the Root node was pushed under
			String RootID = E.getExportIDByObject(R);
			//System.out.println("The Root's XMI id is: " + RootID);
			// closing our stream
			Out.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		//System.out.println("Program finished.");
	}

	//  This is a simple method that creates sample data. In real life, the data could come from a 
	// database, an EJB, or some other source.
	public static Root getSampleDataset() {
		String FileName = getSerializationFileName();
		File F = new File(FileName);
		if (!F.exists()) {
			return null;
		} else {
			//System.out.println("Reading the serialized DataSet");
			Root R = (Root) ObjectSerializer.Read(F);
			//System.out.println("***** Printing the serialized DataSet *****");
			//Test(R);
			return R;
		}
	}

	public static void UpdateSampleDataset(Root R) {
		String FileName = getSerializationFileName();
		File F = new File(FileName);
		ObjectSerializer.Write(R, F);
	}

	public static String getSerializationFileName() {
		String ClassName = WDO4JSEmitterTest.class.getName().replace('.', '\\') + ".class";
		String ClassLocation =
			WDO4JSEmitterTest
				.class
				.getProtectionDomain()
				.getCodeSource()
				.getLocation()
				.toString()
				.substring("file:/".length())
				.replace('/', '\\');
		//System.out.println("ClassName: " + ClassName);
		//System.out.println("ClassLocation: " + ClassLocation);

		if (ClassLocation.endsWith(ClassName) == true) {
			ClassLocation = ClassLocation.substring(0, ClassLocation.length() - ClassName.length());
			//System.out.println("ClassLocation: " + ClassLocation);
		}

		String SerializationFile = ClassLocation + "PortfolioData.bin";
		//System.out.println("Checking for serialized Portfolio file: " + SerializationFile);

		return SerializationFile;
	}

	public static void Test(Root R) {
		System.out.println();
		//System.out.println(
		//	"-------------------------------------------------------------------------------------------------------------------------------------------------");
		//System.out.println("Root= " + R);
		//System.out.println(
		//	"-------------------------------------------------------------------------------------------------------------------------------------------------");
		User[] users = R.getUsers();
		for (int i = 0; i < users.length; ++i) {
			User U = users[i];
		//	System.out.println(
		//		"User: ID=" + U.getId() + "; LastName=" + U.getLastName() + "; RefNum=" + U.getRefNum() + ";");
			Portfolio[] portfolios = U.getPortfolios();
			for (int j = 0; j < portfolios.length; ++j) {
				Portfolio Port = portfolios[j];
			//	System.out.println("   Portfolio: Name=" + Port.getPortfolioName() + ";");
				Position[] positions = Port.getPositions();
				for (int k = 0; k < positions.length; ++k) {
					Position Pos = positions[k];
			//		System.out.println(
			//			"      Position: Symbol="
			//				+ Pos.getStock().getSymbol()
			//				+ "; Quantity="
			//				+ Pos.getQuantity()
			//				+ "; Price="
			//				+ Pos.getPrice()
			//				+ ";");
			//		System.out.println(
			//			"          -> Stock: Symbol="
			//				+ Pos.getStock().getSymbol()
			//				+ "; Price="
			//				+ Pos.getStock().getCurrentPrice()
			//				+ ";");
				}
			}
		}
		Stock[] stocks = R.getStocks();
		for (int i = 0; i < stocks.length; ++i) {
			Stock S = stocks[i];
		//	System.out.println(
		//		"Stock: Symbol="
		//			+ S.getSymbol()
		//			+ "; Price="
		//			+ S.getCurrentPrice()
		//			+ "; change="
		//			+ S.getChange()
		//			+ ";");
		}
		//System.out.println(
	//		"-------------------------------------------------------------------------------------------------------------------------------------------------");
		//System.out.println();
	}
}
