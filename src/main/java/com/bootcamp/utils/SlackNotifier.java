package com.bootcamp.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SlackNotifier {

	/**
	 * Sends a text message to the Slack channel configured in config.properties.
	 * @param message The text message to send.
	 */
	public static void sendNotification(String message) {
		// 1. Read the Slack URL from our configuration properties file
		String slackWebhookUrl = ConfigReader.getProperty("slackWebhookUrl");
		
		// If the webhook is not configured or still has the placeholder, skip sending
		if (slackWebhookUrl == null || slackWebhookUrl.trim().isEmpty() || slackWebhookUrl.contains("YOUR_SLACK_WEBHOOK_URL")) {
			System.out.println("Slack Webhook URL is not configured. Skipping Slack notification.");
			return;
		}

		try {
			// 2. Create an HttpClient (Java's built-in tool to browse/send data over the web)
			HttpClient client = HttpClient.newBuilder()
					.connectTimeout(Duration.ofSeconds(10)) // Wait at most 10 seconds to connect
					.build();

			// 3. Format the message into JSON format that Slack expects: {"text": "Your Message Here"}
			// We escape double quotes: \"
			String jsonPayload = "{\"text\": \"" + message + "\"}";

			// 4. Build our HTTP POST request (like sending a package to a specific address)
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(slackWebhookUrl)) // The address (URL)
					.header("Content-Type", "application/json") // Inform Slack we are sending JSON data
					.POST(HttpRequest.BodyPublishers.ofString(jsonPayload)) // Attach the message body
					.build();

			// 5. Send the request asynchronously (in the background)
			// This ensures that even if Slack is slow or down, it won't slow down or crash our test execution.
			HttpResponse<String> response = client.send(request
					, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				System.out.println("Slack notification sent successfully!");
			} else {
				System.out.println("Failed to send Slack notification. Response code: " 
			+ response.statusCode());
			}
		} catch (Exception e) {
			System.out.println("Error sending Slack notification: " + e.getMessage());
		}
	}
}