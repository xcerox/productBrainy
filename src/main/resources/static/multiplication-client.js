let attempt = null;
let resultMessage = null;
let userAlias = null;
let factorB = null;
let factorA = null;

const setUpHttpRequest = () => {
	const JSON_CONTENT_TYPE = 'application/json';

	$.ajaxSetup({
		headers: {
			'Content-Type': JSON_CONTENT_TYPE,
			'Accept': JSON_CONTENT_TYPE
		}
	});
}

const getFactors = () => {
	$.get("/multiplications/random", data => {
		attempt.val("");
		resultMessage.val("");
		userAlias.val("");
		factorB.empty().append(data.factorB);
		factorA.empty().append(data.factorA);
	})
}

const postAttemp = data => {
	$.post("/results", JSON.stringify(data), response => {
		if (response.correct) {
			resultMessage.empty().append("The result is correct! Congratulations!");
		} else {
			resultMessage.empty().append("Oops that's not correct! But keep trying!");
		}
	});
}

$(document).ready(() => {
	setUpHttpRequest();

	const form = $("#attempt-form");

	attempt = form.find("input[name='result-attempt']");
	userAlias = form.find("input[name='user-alias']");
	factorB = $('.multiplication-b');
	factorA = $('.multiplication-a');
	resultMessage = $('.result-message')

	getFactors();

	form.submit(event => {
		event.preventDefault();

		const data = {
			user: { alias: userAlias.val() },
			multiplication: { factorA: factorA.text(), factorB: factorB.text() },
			resultAttempt: attempt.val()
		};

		postAttemp(data);
	});
});