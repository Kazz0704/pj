import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;

public class animation extends JPanel {
	private JProgressBar progressBar = new JProgressBar();
	private int animationTime;
	private JTextArea textArea = new JTextArea();
	private JLabel label = new JLabel("Welcome to our pizza shop!");
	private int count = 0, index = 0;

	public animation(int animationTime) {
		this.animationTime = animationTime;
		setLayout(null);
		setBackground(new Color(34, 40, 44));
		setBounds(0, 0, 1000, 1000);

		Font font = new Font("Courier New", Font.PLAIN, 16);
		JLabel label = new JLabel("Welcome, Esteemed Visitor!");
		label.setBounds(100, 200, 900,55);
		label.setFont(new Font("Academy Engraved LET", Font.BOLD, 45));
		label.setForeground(new Color(3, 169, 244));
		label.setVisible(false);
		add(label);

		String text2 = "Level up your dining game: Order online like a boss with our slick system.";
		JLabel label2 = new JLabel("");
		label2.setBounds(100, 250, 900, 50);
		label2.setFont(new Font("Academy Engraved LET", Font.BOLD, 25));
		label2.setForeground(Color.WHITE);
		label2.setVisible(false);
		add(label2);

		// 設定文本框
		textArea.setBounds(100, 100, 800, 400);
		textArea.setFont(font);
		textArea.setForeground(new Color(3, 169, 244));
		textArea.setBackground(new Color(34, 40, 44));
		textArea.setEditable(false);
		add(textArea);

		// 設定定時器來模擬程式啟動時的初始化
		Timer timer = new Timer(animationTime / 1000, new ActionListener() {
			int progress = 0;
			String t = "\"#include <stdio.h>\\\\n\\n\"\n" + "+ \"#include <stdlib.h>\\\\n\\n\"\n"
					+ "+ \"#include <string.h>\\\\n\\n\"\n" + "+ \"#include <unistd.h>\\\\n\\n\"\n"
					+ "+ \"#include <sys/socket.h>\\\\n\\n\"\n" + "+ \"#include <netinet/in.h>\\\\n\\n\"\n"
					+ "+ \"#include <arpa/inet.h>\\\\n\\n\"\n" + "+ \"#include <pthread.h>\\\\n\\n\"\n" + "+ \"\\n\"\n"
					+ "+ \"#define PORT 31337\\\\n\\n\"\n" + "+ \"#define MAX_THREADS 10\\\\n\\n\"\n" + "+ \"\\n\"\n"
					+ "+ \"void *worker_thread(void *arg) {\\\\n\\n\"\n"
					+ "+ \"    int connfd = *((int *)arg);\\\\n\\n\"\n" + "+ \"    char buffer[1024];\\\\n\\n\"\n"
					+ "+ \"\\n\"\n"
					+ "+ \"    printf(\\\"[+] New thread started for client %d\\\\n\\\", connfd);\\\\n\\n\"\n"
					+ "+ \"\\n\"\n" + "+ \"    if (recv(connfd, buffer, sizeof(buffer), 0) == -1) {\\\\n\\n\"\n"
					+ "+ \"        perror(\\\"receive failed\\\");\\\\n\\n\"\n"
					+ "+ \"        close(connfd);\\\\n\\n\"\n" + "+ \"        return NULL;\\\\n\\n\"\n"
					+ "+ \"    }\\\\n\\n\"\n" + "+ \"\\n\"\n"
					+ "+ \"    printf(\\\"[+] Received message from client %d: %s\\\", connfd, buffer);\\\\n\\n\"\n"
					+ "+ \"\\n\"\n" + "+ \"    if (send(connfd, buffer, strlen(buffer), 0) == -1) {\\\\n\\n\"\n"
					+ "+ \"        perror(\\\"send failed\\\");\\\\n\\n\"\n" + "+ \"        close(connfd);\\\\n\\n\"\n"
					+ "+ \"        return NULL;\\\\n\\n\"\n" + "+ \"    }\\\\n\\n\"\n" + "+ \"\\n\"\n"
					+ "+ \"    printf(\\\"[+] Sent message to client %d: %s\\\", connfd, buffer);\\\\n\\n\"\n"
					+ "+ \"\\n\"\n" + "+ \"    close(connfd);\\\\n\\n\"\n" + "+ \"    return NULL;\\\\n\\n\"\n"
					+ "+ \"}\\\\n\\n\"\n" + "+ \"\\n\"\n" + "+ \"int main(int argc, char **argv) {\\\\n\\n\"\n"
					+ "+ \"    int sockfd, connfd;\\\\n\\n\"\n"
					+ "+ \"    struct sockaddr_in servaddr, cliaddr;\\\\n\\n\"\n"
					+ "+ \"    pthread_t threads[MAX_THREADS];\\\\n\\n\"\n"
					+ "+ \"    int thread_args[MAX_THREADS];\\\\n\\n\"\n" + "+ \"    int thread_count = 0;\\\\n\\n\"\n"
					+ "+ \"\\n\"\n" + "+ \"    if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {\\\\n\\n\"\n"
					+ "+ \"        perror(\\\"socket creation failed\\\");\\\\n\\n\"\n"
					+ "+ \"        exit(EXIT_FAILURE);\\\\n\\n\"\n" + "+ \"    }\\\\n\\n\"\n" + "+ \"\\n\"\n"
					+ "+ \"    memset(&servaddr, 0, sizeof(servaddr));\\\\n\\n\"\n"
					+ "+ \"    servaddr.sin_family = AF_INET;\\\\n\\n\"\n"
					+ "+ \"    servaddr.sin_addr.s_addr = INADDR_ANY;\\\\n\\n\"\n"
					+ "+ \"    servaddr.sin_port = htons(PORT);\\\\n\\n\"\n" + "+ \"\\n\"\n"
					+ "+ \"    if (bind(sockfd, (struct sockaddr *)&servaddr, sizeof(servaddr)) == -1) {\\\\n\\n\"\n"
					+ "+ \"        perror(\\\"bind failed\\\");\\\\n\\n\"\n"
					+ "+ \"        exit(EXIT_FAILURE);\\\\n\\n\"\n" + "+ \"    }\\\\n\\n\"\n" + "+ \"\\n\"\n"
					+ "+ \"    if (listen(sockfd, 10) == -1) {\\\\n\\n\"\n"
					+ "+ \"        perror(\\\"listen failed\\\");\\\\n\\n\"\n"
					+ "+ \"        exit(EXIT_FAILURE);\\\\n\\n\"\n" + "+ \"    }\\\\n\\n\"\n" + "+ \"\\n\"\n"
					+ "+ \"    printf(\\\"Server listening on port %d...\\\\n\\\", PORT);\\\\n\\n\"\n" + "+ \"\\n\"\n"
					+ "+ \"    while (1) {\\\\n\\n\"\n"
					+ "+ \"        socklen_t cliaddr_len = sizeof(cliaddr);\\\\n\\n\"\n"
					+ "+ \"        connfd = accept(sockfd, (struct sockaddr *)&cliaddr, &cliaddr_len);\\\\n\\n\"\n"
					+ "+ \"        if (connfd == -1) {\\\\n\\n\"\n"
					+ "+ \"            perror(\\\"accept failed\\\");\\\\n\\n\"\n"
					+ "+ \"            continue;\\\\n\\n\"\n" + "+ \"        }\\\\n\\n\"\n" + "+ \"\\n\"\n"
					+ "+ \"        printf(\\\"[+] New client connected: %s:%d\\\\n\\\", inet_ntoa(cliaddr.sin_addr), ntohs(cliaddr.sin_port));\\\\n\\n\"\n"
					+ "+ \"\\n\"\n" + "+ \"        thread_args[thread_count] = connfd;\\\\n\\n\"\n"
					+ "+ \"        pthread_create(&threads[thread_count], NULL, worker_thread, &thread_args[thread_count]);\\\\n\\n\"\n"
					+ "+ \"\\n\"\n" + "+ \"        if (++thread_count >= MAX_THREADS) {\\\\n\\n\"\n"
					+ "+ \"            break;\\\\n\\n\"\n" + "+ \"        }\\\\n\\n\"\n" + "+ \"    }\\\\n\\n\"\n"
					+ "+ \"\\n\"\n" + "+ \"    printf(\\\"loading...\\\\n\\\");\\\\n\\n\";\n" + "";;
			String text = t.repeat(3);

			@Override
			public void actionPerformed(ActionEvent evt) {
				if (progress == 3600) {
					try {
						Thread.sleep(300);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (progress == 6300) {
					try {
						Thread.sleep(300);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				textArea.setText(text.substring(progress));
				try {
					playSound("/audio/key1.wav");
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
				progress += 90;
				if (progress >= text.length()) {
					((Timer) evt.getSource()).stop();
					textArea.setVisible(false);
					label.setVisible(true);
					label2.setVisible(true);
					try {
						playSound("/audio/debut.wav");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Timer timer = new Timer(20, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (index < text2.length()) {
								if (index == 27) {
									count++;
									if (count == 20) { // 等待10秒
										index++;
										count = 0;
									}
								} else {
									label2.setText(text2.substring(0, index++));
								}
							} else {
								((Timer) e.getSource()).stop();
							}
						}
					});
					timer.start();
				}
			}
		});
		timer.start();
	}

	public void playSound(String s) throws Exception {
		File soundFile = new File(getClass().getResource(s).getPath());
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		AudioFormat format = audioInputStream.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		if (!AudioSystem.isLineSupported(info)) {
			System.out.println("Line not supported");
			return;
		}
		Clip clip = (Clip) AudioSystem.getLine(info);
		clip.open(audioInputStream);
		clip.start();
		Thread.sleep(clip.getMicrosecondLength() / 5000); // Wait for the sound to finish playing
	}

}
