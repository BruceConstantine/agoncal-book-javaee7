package org.agoncal.book.javaee7.chapter13.ex04;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Sender {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Resource(lookup = "jms/javaee6/ConnectionFactory")
  private static ConnectionFactory connectionFactory;
  @Resource(lookup = "jms/javaee6/Queue")
  private static Queue queue;

  // ======================================
  // =           Public Methods           =
  // ======================================

  public static void main(String[] args) {

    try {
      // Creates the needed artifacts to connect to the queue
      Connection connection = connectionFactory.createConnection();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      MessageProducer producer = session.createProducer(queue);

      // Sends a text message to the queue
      TextMessage message = session.createTextMessage();
      message.setText("This is a text message sent at " + new Date());
      producer.send(message);
      System.out.println("\nMessage sent !");

      connection.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.exit(0);
  }
}