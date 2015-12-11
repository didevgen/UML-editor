package ua.nure.sigma.code.fileManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchiveGenerator {
	final static int BUFFER = 2048;

	public static boolean createZipArchive(String srcFolder) {
		try {
			BufferedInputStream origin = null;
			FileOutputStream dest = new FileOutputStream(new File(srcFolder + ".zip"));
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
			byte data[] = new byte[BUFFER];

			File subDir = new File(srcFolder);
			String subdirList[] = subDir.list();
			for (String sd : subdirList) {
				File f = new File(srcFolder + "/" + sd);
				if (f.isDirectory()) {
					String files[] = f.list();

					for (int i = 0; i < files.length; i++) {
						System.out.println("Adding: " + files[i]);
						FileInputStream fi = new FileInputStream(srcFolder + "/" + sd + "/" + files[i]);
						origin = new BufferedInputStream(fi, BUFFER);
						ZipEntry entry = new ZipEntry(sd + "/" + files[i]);
						out.putNextEntry(entry);
						int count;
						while ((count = origin.read(data, 0, BUFFER)) != -1) {
							out.write(data, 0, count);
							out.flush();
						}

					}
				} else {
					FileInputStream fi = new FileInputStream(f);
					origin = new BufferedInputStream(fi, BUFFER);
					ZipEntry entry = new ZipEntry(sd);
					out.putNextEntry(entry);
					int count;
					while ((count = origin.read(data, 0, BUFFER)) != -1) {
						out.write(data, 0, count);
						out.flush();
					}

				}
			}
			origin.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			return false;

		}
		return true;
	}
}
