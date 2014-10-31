package com.ibm.util.node;

import com.ibm.broker.config.appdev.InputTerminal;
import com.ibm.broker.config.appdev.Node;
import com.ibm.broker.config.appdev.NodeProperty;
import com.ibm.broker.config.appdev.OutputTerminal;

/*** 
 * <p>  <I>UnZipFileNodeUDN</I> instance</p>
 * <p></p>
 */
public class UnZipFileNodeUDN extends Node {

	private static final long serialVersionUID = 1L;

	// Node constants
	protected final static String NODE_TYPE_NAME = "com/ibm/util/node/UnZipFileNode";
	protected final static String NODE_GRAPHIC_16 = "platform:/plugin/UnZipFilePluginPrj/icons/full/obj16/com/ibm/util/node/UnZipFile.gif";
	protected final static String NODE_GRAPHIC_32 = "platform:/plugin/UnZipFilePluginPrj/icons/full/obj30/com/ibm/util/node/UnZipFile.gif";

	protected final static String PROPERTY_ZIPFILEPATH = "zipFilePath";
	protected final static String PROPERTY_UNZIPFILEPATH = "unZipFilePath";

	protected NodeProperty[] getNodeProperties() {
		return new NodeProperty[] {
			new NodeProperty(UnZipFileNodeUDN.PROPERTY_ZIPFILEPATH,		NodeProperty.Usage.MANDATORY,	false,	NodeProperty.Type.STRING, null,"","",	"com/ibm/util/node/UnZipFile",	"UnZipFilePluginPrj"),
			new NodeProperty(UnZipFileNodeUDN.PROPERTY_UNZIPFILEPATH,		NodeProperty.Usage.MANDATORY,	false,	NodeProperty.Type.STRING, null,"","",	"com/ibm/util/node/UnZipFile",	"UnZipFilePluginPrj")
		};
	}

	public UnZipFileNodeUDN() {
	}

	public final InputTerminal INPUT_TERMINAL_IN = new InputTerminal(this,"InTerminal.in");
	@Override
	public InputTerminal[] getInputTerminals() {
		return new InputTerminal[] {
			INPUT_TERMINAL_IN
	};
	}

	public final OutputTerminal OUTPUT_TERMINAL_OUT = new OutputTerminal(this,"OutTerminal.out");
	@Override
	public OutputTerminal[] getOutputTerminals() {
		return new OutputTerminal[] {
			OUTPUT_TERMINAL_OUT
		};
	}

	@Override
	public String getTypeName() {
		return NODE_TYPE_NAME;
	}

	protected String getGraphic16() {
		return NODE_GRAPHIC_16;
	}

	protected String getGraphic32() {
		return NODE_GRAPHIC_32;
	}

	/**
	 * Set the <I>UnZipFileNodeUDN</I> "<I>zipFilePath</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>zipFilePath</I>"
	 */
	public UnZipFileNodeUDN setZipFilePath(String value) {
		setProperty(UnZipFileNodeUDN.PROPERTY_ZIPFILEPATH, value);
		return this;
	}

	/**
	 * Get the <I>UnZipFileNodeUDN</I> "<I>zipFilePath</I>" property
	 * 
	 * @return String; the value of the property "<I>zipFilePath</I>"
	 */
	public String getZipFilePath() {
		return (String)getPropertyValue(UnZipFileNodeUDN.PROPERTY_ZIPFILEPATH);
	}

	/**
	 * Set the <I>UnZipFileNodeUDN</I> "<I>unZipFilePath</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>unZipFilePath</I>"
	 */
	public UnZipFileNodeUDN setUnZipFilePath(String value) {
		setProperty(UnZipFileNodeUDN.PROPERTY_UNZIPFILEPATH, value);
		return this;
	}

	/**
	 * Get the <I>UnZipFileNodeUDN</I> "<I>unZipFilePath</I>" property
	 * 
	 * @return String; the value of the property "<I>unZipFilePath</I>"
	 */
	public String getUnZipFilePath() {
		return (String)getPropertyValue(UnZipFileNodeUDN.PROPERTY_UNZIPFILEPATH);
	}

	public String getNodeName() {
		String retVal = super.getNodeName();
		if ((retVal==null) || retVal.equals(""))
			retVal = "UnZipFile";
		return retVal;
	};
}
