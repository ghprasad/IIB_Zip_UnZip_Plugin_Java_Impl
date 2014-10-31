import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbInputTerminal;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbNode;
import com.ibm.broker.plugin.MbNodeInterface;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;

public class ZipFileJavaPluginNode extends MbNode implements MbNodeInterface {

	private String filePath = "";
	private String zipFilePath = "";
	private String filter = "";
	public ZipFileJavaPluginNode() throws MbException {

		createInputTerminal("in");
		createOutputTerminal("out");
	}

	public void evaluate(MbMessageAssembly inAssembly,
			MbInputTerminal inputTerminal) throws MbException {

		MbOutputTerminal outTerminal = getOutputTerminal("out");

		MbMessage inMessage = inAssembly.getMessage();
		MbMessage outMessage = new MbMessage(inMessage);
		MbMessageAssembly outAssembly = new MbMessageAssembly(inAssembly,
				outMessage);

		try {
			File file = null;

			String _zipFile = "";
			String _filePath = "";
			MbElement fileLocalEnv = inAssembly.getLocalEnvironment()
					.getRootElement().getFirstElementByPath("File");
			if (fileLocalEnv != null) {
				_zipFile = fileLocalEnv.getFirstElementByPath("Zip")
						.getFirstElementByPath("Path").getValueAsString()
						+ fileLocalEnv.getFirstElementByPath("Zip")
								.getFirstElementByPath("Name")
								.getValueAsString();
				_filePath = fileLocalEnv.getFirstElementByPath("UnZip")
						.getFirstElementByPath("Path").getValueAsString();
				file = new File(_filePath);
			} else {
				_zipFile = getZipFilePath();
				_filePath = getFilePath();
				file = new File(_filePath);
			}
			File files[] = file.listFiles();
			zipFile(files,_zipFile);
			outTerminal.propagate(outAssembly);
		} catch (Exception e) {
			// Consider replacing Exception with type(s) thrown by user code
			// Example handling ensures all exceptions are re-thrown to be
			// handled in the flow
			throw new MbUserException(this, "evaluate()", "", "", e.toString(),
					null);
		} finally {

			// clear the outMessage even if there’s an exception
			outMessage.clearMessage();
		}

		

	}

	public static String getNodeName() {
		return "ZipFileNode";
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getZipFilePath() {
		return this.zipFilePath;
	}

	public void setZipFilePath(String zipFilePath) {
		this.zipFilePath = zipFilePath;
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

	public void zipFile(File files[], String zipFilePath) {

		try{
			if (files.length > 0) {
				
				FileOutputStream fos = new FileOutputStream(zipFilePath);
				ZipOutputStream zos = new ZipOutputStream(fos);
				//File _zipfile = new File(zipFilePath);
				String filter = getFilter();
				if(filter!=null){
					filter=filter.replace("*", "");
				}
				int fileSize=files.length;
				for (int i = 0; (i < fileSize); i++) {
					String path = files[i].getAbsolutePath();
					String fileName = files[i].getName();
					
					if(filter!=null){
						if(!fileName.toLowerCase().contains(filter.toLowerCase())){
							addToZipFile(path, zos);
							files[i].delete();		
						}
					}else{
						addToZipFile(path, zos);
						files[i].delete();	
					}
				}
				zos.close();
				fos.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onDelete() {
		// Do Nothing for now
	}
	
	public void addToZipFile(String fileNamePath, ZipOutputStream zos)
			throws FileNotFoundException, IOException {
		File file = new File(fileNamePath);
		String fileName = file.getName();
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zos.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}
		
		zos.closeEntry();
		fis.close();
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	
//	public static void main(String arg[])throws Exception{
//		
//		File f = new File("C:/test/in");
//		zipFile(f.listFiles(),"C:/test/out/TEST.zip");
//	}

}
