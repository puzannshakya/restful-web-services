package com.assignment.departmentstorequeues;

/**
 * A simple empty queue exception.
 * 
 * @author Ed Grzyb
 */
public class EmptyQueueException extends RuntimeException {
  /**
   * Just provide the default constructor. We don't want
   * to allow the user to choose the message associated
   * with the exception.
   */
  public EmptyQueueException() {
    super("Queue is empty.");
  }
}
