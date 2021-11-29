package Advanced.multithreading.Communication;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author Feue
 * @create 2021-11-29 14:33
 */
public class PipeTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        PipedReader reader = new PipedReader();
        PipedWriter writer = new PipedWriter();

        writer.connect(reader);

        new Thread(new ReaderThread(reader)).start();
        Thread.sleep(50);
        new Thread(new WriterThread(writer)).start();
    }

    static class ReaderThread implements Runnable {
        private PipedReader reader;

        public ReaderThread(PipedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            System.out.println("This is reader");
            int receive = 0;
            try {
                while ((receive = reader.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class WriterThread implements Runnable {
        private PipedWriter writer;

        public WriterThread(PipedWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            System.out.println("This is writer");
            try {
                writer.write("test");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
