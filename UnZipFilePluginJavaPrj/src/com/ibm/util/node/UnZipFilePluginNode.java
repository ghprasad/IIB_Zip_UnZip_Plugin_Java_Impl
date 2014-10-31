package com.ibm.util.node;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbInputTerminal;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbNode;
import com.ibm.broker.plugin.MbNodeInterface;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;

public class UnZipFilePluginNode extends MbNode implements MbNodeInterface {

	private String _unZipFilePath = "";
	private String _zipFilePath = "";

	public UnZipFilePluginNode() throws MbException {

		createInputTerminal("in");
		createOutputTerminal("out");
	}

	public void evaluate(MbMessageAssembly inAssembly,
			MbInputTerminal inputTerminal) throws MbException {

		MbOutputTerminal outTerminal = getOutputTerminal("out");

		MbMessage inMessage = inAssembly.getMessage();
		MbMessage outMessage = new MbMessage(inMessage);
		MbMessageAssembly outAssembly = new MbMessageAssembly(inAssembly,outMessage);

		try {
			String zipFile ="";
			String unzipFile ="";
			MbElement fileLocalEnv = inAssembly.getLocalEnvironment().getRootElement().getFirstElementByPath("File");
			if (fileLocalEnv!=null){
				zipFile   = fileLocalEnv.getFirstElementByPath("Zip").getFirstElementByPath("Path").getValueAsString()+fileLocalEnv.getFirstElementByPath("Zip").getFirstElementByPath("Name").getValueAsString();
				unzipFile = fileLocalEnv.getFirstElementByPath("UnZip").getFirstElementByPath("Path").getValueAsString();
			}else{
				zipFile   = getZipFilePath();
				unzipFile = getUnZipFilePath();
			}
			unzipFile(zipFile, unzipFile);
			outTerminal.propagate(outAssembly);
		} catch (Exception e) {
			// Consider replacing Exception with type(s) thrown by user code
			// Example handling ensures all exceptions are re-thrown to be
			// handled in the flow
			throw new MbUserException(this, "evaluate()", "", "", e.toString(),null);
		} finally {

			// clear the outMessage even if there’s an exception
			outMessage.clearMessage();
		}
		
		

	}

	public static String getNodeName() {
		return "UnZipFileNode";
	}

	public String getUnZipFilePath() {
		return this._unZipFilePath;
	}

	public void setUnZipFilePath(String unZipFilePath) {
		this._unZipFilePath = unZipFilePath;
	}

	public String getZipFilePath() {
		return this._zipFilePath;
	}

	public void setZipFilePath(String zipFilePath) {
		this._zipFilePath = zipFilePath;
	}

	public void copyMessageHeaders(MbMessage inMessage, MbMessage outMessage)
			throws MbException {
		MbElement outRoot = outMessage.getRootElement();

		// iterate though the headers starting with the first child of the root
		// element
		MbElement header = inMessage.getRootElement().getFirstChild();
		while (header != null && header.getNextSibling() != null) // stop before
		// the last
		// child
		// (body)
		{
			// copy the header and add it to the out message
			outRoot.addAsLastChild(header.copy());
			// move along to next header
			header = header.getNextSibling();
		}

	}

	public void unzipFile(String zipFilePath, String unZipFilePath) {

		FileInputStream fis = null;
		File zipFile = new File(zipFilePath);
		ZipInputStream zipIs = null;
		ZipEntry zEntry = null;
		try {
			fis = new FileInputStream(zipFile);
			zipIs = new ZipInputStream(new BufferedInputStream(fis));
			while ((zEntry = zipIs.getNextEntry()) != null) {
				try {
					byte[] tmp = new byte[4 * 1024];
					FileOutputStream fos = null;
					String opFilePath = unZipFilePath + zEntry.getName();
					fos = new FileOutputStream(opFilePath);
					int size = 0;
					while ((size = zipIs.read(tmp)) != -1) {
						fos.write(tmp, 0, size);
					}
					fos.flush();
					fos.close();
				} catch (Exception ex) {

				}
			}
			zipIs.close();
			zipFile.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onDelete() {
		// Do Nothing for now
	}

}
