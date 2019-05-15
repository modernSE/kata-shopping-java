The Brettspiel-Emporium Coding Kata

Ferdi's Brettspiel Emporium is a Boardgame Shop that wants to start an online shop. To that end, the owner has
started developing something to the best of his knowledge. He needs to use several APIs and components that he's
not in control of. All of those have been put into the API package. They cannot be changed.

The owner has also written some tests. He's not entirely happy with his tests concerning the payment process. But he's
particularly dissatisfield when it comes to testing that the amount the customers are charged for is the actual sum of
prices of items in the shopping cart. Prices are subject to micro fluctuations, so every time he's asking the
ItemInformationProvider for an item's price, he gets a slightly different one. To him, that's no good. He thinks that
for testing purposes, it would be neat to be able to supply a bogus implementation, that returns the same price every
time. But that's not even the biggest issue. Lately, he hasn't even been able to get the tests to run because his
authentication keeps getting rejected. Maybe he'll need to do something about the authentication provider, as well?

However, his implementation doesn't seem to easily support those things. Can you help him out? And while you're at it,
why don't you take a look at how the payment's implemented, too? The owner has a hunch that adding new payment services
may prove to be a problem...

TL;DR:
* Start by getting the tests to work. What can you do about the authentication process to fix things?
* What can you do about the ItemInformationProvider for testing purposes?
* Prove your Concept by summarizing the price of items in a shopping cart. Working with a tolerance other than 0 is not
  allowed.
* Continue your refactoring and see if you can also streamline the payment process.