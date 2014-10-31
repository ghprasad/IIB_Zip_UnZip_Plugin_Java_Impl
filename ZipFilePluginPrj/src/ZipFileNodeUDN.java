import com.ibm.broker.config.appdev.InputTerminal;
import com.ibm.broker.config.appdev.Node;
import com.ibm.broker.config.appdev.NodeProperty;
import com.ibm.broker.config.appdev.OutputTerminal;

/*** 
 * <p>  <I>ZipFileNodeUDN</I> instance</p>
 * <p></p>
 */
public class ZipFileNodeUDN extends Node {

	private static final long serialVersionUID = 1L;

	// Node constants
	protected final static String NODE_TYPE_NAME = "ZipFileNode";
	protected final static String NODE_GRAPHIC_16 = "platform:/plugin/ZipFilePluginPrj/icons/full/obj16/ZipFile.gif";
	protected final static String NODE_GRAPHIC_32 = "platform:/plugin/ZipFilePluginPrj/icons/full/obj30/ZipFile.gif";

	protected final static String PROPERTY_FILEPATH = "filePath";
	protected final static String PROPERTY_ZIPFILEPATH = "zipFilePath";
	protected final static String PROPERTY_FILTER = "filter";

	protected NodeProperty[] getNodeProperties() {
		return new NodeProperty[] {
			new NodeProperty(ZipFileNodeUDN.PROPERTY_FILEPATH,		NodeProperty.Usage.MANDATORY,	false,	NodeProperty.Type.STRING, null,"","",	"ZipFile",	"ZipFilePluginPrj"),
			new NodeProperty(ZipFileNodeUDN.PROPERTY_ZIPFILEPATH,		NodeProperty.Usage.MANDATORY,	false,	NodeProperty.Type.STRING, null,"","",	"ZipFile",	"ZipFilePluginPrj"),
			new NodeProperty(ZipFileNodeUDN.PROPERTY_FILTER,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.STRING, null,"","",	"ZipFile",	"ZipFilePluginPrj")
		};
	}

	public ZipFileNodeUDN() {
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
	 * Set the <I>ZipFileNodeUDN</I> "<I>filePath</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>filePath</I>"
	 */
	public ZipFileNodeUDN setFilePath(String value) {
		setProperty(ZipFileNodeUDN.PROPERTY_FILEPATH, value);
		return this;
	}

	/**
	 * Get the <I>ZipFileNodeUDN</I> "<I>filePath</I>" property
	 * 
	 * @return String; the value of the property "<I>filePath</I>"
	 */
	public String getFilePath() {
		return (String)getPropertyValue(ZipFileNodeUDN.PROPERTY_FILEPATH);
	}

	/**
	 * Set the <I>ZipFileNodeUDN</I> "<I>zipFilePath</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>zipFilePath</I>"
	 */
	public ZipFileNodeUDN setZipFilePath(String value) {
		setProperty(ZipFileNodeUDN.PROPERTY_ZIPFILEPATH, value);
		return this;
	}

	/**
	 * Get the <I>ZipFileNodeUDN</I> "<I>zipFilePath</I>" property
	 * 
	 * @return String; the value of the property "<I>zipFilePath</I>"
	 */
	public String getZipFilePath() {
		return (String)getPropertyValue(ZipFileNodeUDN.PROPERTY_ZIPFILEPATH);
	}

	/**
	 * Set the <I>ZipFileNodeUDN</I> "<I>filter</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>filter</I>"
	 */
	public ZipFileNodeUDN setFilter(String value) {
		setProperty(ZipFileNodeUDN.PROPERTY_FILTER, value);
		return this;
	}

	/**
	 * Get the <I>ZipFileNodeUDN</I> "<I>filter</I>" property
	 * 
	 * @return String; the value of the property "<I>filter</I>"
	 */
	public String getFilter() {
		return (String)getPropertyValue(ZipFileNodeUDN.PROPERTY_FILTER);
	}

	public String getNodeName() {
		String retVal = super.getNodeName();
		if ((retVal==null) || retVal.equals(""))
			retVal = "ZipFile";
		return retVal;
	};
}
