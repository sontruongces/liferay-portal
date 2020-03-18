/**
 * Mocks the Liferay.Language.get() method, which returns the value for a given language key.
 */

window.Liferay = {
	Language: {
		get: key => key
	}
};

/**
 * Mocks the form submission behavior
 */
HTMLFormElement.prototype.submit = jest.fn();
