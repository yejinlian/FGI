/********************************************
 * 文件操作工具类
 *
 * @author zwq
 * @create 2018-06-12
 *********************************************/

package deepthinking.fgi.util;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	public static String readGzipFile(String targzFile) {

		Logger logger = LoggerFactory.getLogger(FileUtils.class);

		FileInputStream fis = null;
		ArchiveInputStream in = null;
		BufferedInputStream bufferedInputStream = null;
		StringBuffer line = new StringBuffer();

		try {
			fis = new FileInputStream(targzFile);
			BZip2CompressorInputStream is = new BZip2CompressorInputStream(new BufferedInputStream(fis));
			in = new ArchiveStreamFactory().createArchiveInputStream("tar", is);
			bufferedInputStream = new BufferedInputStream(in);

			TarArchiveEntry entry = null;
			LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
			final int maxsize = 10;
			ExecutorService executor = new ThreadPoolExecutor(maxsize, maxsize, 0l, TimeUnit.MILLISECONDS, queue);
			entry = (TarArchiveEntry) in.getNextEntry();
			while (entry != null) {
				long size = 0;
				String name = entry.getName();
				if (!name.endsWith("md5.txt")) {
					entry = (TarArchiveEntry) in.getNextEntry();
					continue;
				}
				String[] names = name.split("/");
				String fileName = targzFile;

				for (int i = 0; i < names.length; i++) {
					String str = names[i];
					fileName = fileName + File.separator + str;
				}
				if (!fileName.endsWith("/")) {
					size = entry.getSize();

				}
				if (size > 0) {
					byte[] b = new byte[(int) entry.getSize()];
					int len = 0;
					while ((len = in.read(b)) != -1) {
						line.append(new String(b, 0, len, "utf-8"));
					}
				} else {
				}
				entry = (TarArchiveEntry) in.getNextEntry();
			}
			in.close();
			fis.close();
			bufferedInputStream.close();

			executor.shutdown();
		} catch (Exception e1) {
			logger.error(e1.getMessage());
		}

		return line.toString();

	}

	/**
	 * 读取txt文件内容,有效防止io阻塞
	 * @param filePath
	 * @return
	 */
	public static String readTxtFile(String filePath) {
		String content = null;
		try (Reader reader = new InputStreamReader(new FileInputStream(filePath), "GBK")) {
			StringBuffer sb = new StringBuffer();
			char[] tempchars = new char[1024];
			while (reader.read(tempchars) != -1) {
				sb.append(tempchars);
			}
			content = sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	/*************
	 * 删除Linux文件
	 * @param path
	 * @return
	 */
	public static boolean delFile(String path) {
	    boolean flag = false;
	    File file = new File(path);
	    if (!file.exists()) {
	        return flag;
	    }
	    try{
	        flag = file.delete();
	    }catch (Exception e){
	        e.printStackTrace();
	    }
	    return flag;
	}

}
