package org.oh.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oh.common.helper.IOHelper;

/**
 * 로컬 파일을 읽는다.
 * 
 * @version 1.0.0
 *
 */
public class LocalFileDownloader implements IFileDownloader {
	protected static Log log = LogFactory.getLog(LocalFileDownloader.class);

	/**
	 * @param filePath 파일의 로컬 경로(절대 경로 권장)
	 */
	public Files download(String fileName, String filePath) throws Exception {
		log.trace("Start::download()");
		log.trace("  > fileName: " + fileName);
		log.trace("  > filePath: " + filePath);

		Files files = new Files(filePath, fileName, null);
		FileInputStream fis = null;
		byte[] bytes = null;

		try {
			fileName = filePath + File.separator + files.getFile_name();
			fis = new FileInputStream(fileName);
			bytes = IOHelper.readToEnd(fis);

			files.setFile_bytes(bytes);
		} catch (IOException e) {
			log.error("IOException > ", e);
			log.trace("Throw IOException!");

			throw e;
		} finally {
			IOUtils.closeQuietly(fis);
		}

		files.setFile_size((long) bytes.length);

		log.trace("  > RV(files.size): " + files.getFile_size());
		log.trace("End::download()");

		return files;
	}
}