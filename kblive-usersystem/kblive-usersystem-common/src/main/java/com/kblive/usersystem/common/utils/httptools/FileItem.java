package com.kblive.usersystem.common.utils.httptools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * title: FileItem
 * projectName kbLive
 * description:
 * author 2671242147@qq.com
 * date 2019-08-03 14:11
 ***/
public class FileItem {
    private FileItem.Contract contract;

    public FileItem(File file) {
        this.contract = new FileItem.LocalContract(file);
    }

    public FileItem(String filePath) {
        this(new File(filePath));
    }

    public FileItem(String fileName, byte[] content) {
        this(fileName, (byte[]) content, (String) null);
    }

    public FileItem(String fileName, byte[] content, String mimeType) {
        this.contract = new FileItem.ByteArrayContract(fileName, content, mimeType);
    }

    public FileItem(String fileName, InputStream stream) {
        this(fileName, (InputStream) stream, (String) null);
    }

    public FileItem(String fileName, InputStream stream, String mimeType) {
        this.contract = new FileItem.StreamContract(fileName, stream, mimeType);
    }

    public boolean isValid() {
        return this.contract.isValid();
    }

    public String getFileName() {
        return this.contract.getFileName();
    }

    public String getMimeType() throws IOException {
        return this.contract.getMimeType();
    }

    public long getFileLength() {
        return this.contract.getFileLength();
    }

    public void write(OutputStream output) throws IOException {
        this.contract.write(output);
    }

    public byte[] getContent() {
        return "".getBytes();
    }

    private interface Contract {
        boolean isValid();

        String getFileName();

        String getMimeType();

        long getFileLength();

        void write(OutputStream var1) throws IOException;
    }

    private static class StreamContract implements FileItem.Contract {
        private String fileName;
        private InputStream stream;
        private String mimeType;

        public StreamContract(String fileName, InputStream stream, String mimeType) {
            this.fileName = fileName;
            this.stream = stream;
            this.mimeType = mimeType;
        }

        public boolean isValid() {
            return this.stream != null && this.fileName != null;
        }

        public String getFileName() {
            return this.fileName;
        }

        public String getMimeType() {
            return this.mimeType == null ? "application/octet-stream" : this.mimeType;
        }

        public long getFileLength() {
            return 0L;
        }

        public void write(OutputStream output) throws IOException {
            try {
                byte[] buffer = new byte[4096];
                boolean var3 = false;

                int n;
                while (-1 != (n = this.stream.read(buffer))) {
                    output.write(buffer, 0, n);
                }
            } finally {
                if (this.stream != null) {
                    this.stream.close();
                }

            }

        }
    }

    private static class ByteArrayContract implements FileItem.Contract {
        private String fileName;
        private byte[] content;
        private String mimeType;

        public ByteArrayContract(String fileName, byte[] content, String mimeType) {
            this.fileName = fileName;
            this.content = content;
            this.mimeType = mimeType;
        }

        public boolean isValid() {
            return this.content != null && this.fileName != null;
        }

        public String getFileName() {
            return this.fileName;
        }

        public String getMimeType() {
            return this.mimeType == null ? "application/octet-stream" : this.mimeType;
        }

        public long getFileLength() {
            return (long) this.content.length;
        }

        public void write(OutputStream output) throws IOException {
            output.write(this.content);
        }
    }

    private static class LocalContract implements FileItem.Contract {
        private File file;

        public LocalContract(File file) {
            this.file = file;
        }

        public boolean isValid() {
            return this.file != null && this.file.exists() && this.file.isFile();
        }

        public String getFileName() {
            return this.file.getName();
        }

        public String getMimeType() {
            return "application/octet-stream";
        }

        public long getFileLength() {
            return this.file.length();
        }

        public void write(OutputStream output) throws IOException {
            FileInputStream input = null;

            try {
                input = new FileInputStream(this.file);
                byte[] buffer = new byte[4096];
                boolean var4 = false;

                int n;
                while (-1 != (n = input.read(buffer))) {
                    output.write(buffer, 0, n);
                }
            } finally {
                if (input != null) {
                    input.close();
                }

            }

        }
    }
}
