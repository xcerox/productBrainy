let attempt = null;
let resultMessage = null;
let userAlias = null;
let factorB = null;
let factorA = null;
let statsBody = null;

$(document).ready(() => {
	setUpHttpRequest();

	const form = $("#attempt-form");

	attempt = form.find("input[name='result-attempt']");
	userAlias = form.find("input[name='user-alias']");
	factorB = $('.multiplication-b');
	factorA = $('.multiplication-a');
	resultMessage = $('.result-message');
	statsBody = $('#stats-body');

	getFactors(true);

	form.submit(event => {
		event.preventDefault();
		const alias = userAlias.val();

		const payload = {
			user: { alias },
			multiplication: { factorA: factorA.text(), factorB: factorB.text() },
			product: attempt.val()
		};

		postAttemp(payload);
		updateStats(alias);
		getFactors(false);
	});
});


const setUpHttpRequest = () => {
	const JSON_CONTENT_TYPE = 'application/json';

	$.ajaxSetup({
		headers: {
			'Content-Type': JSON_CONTENT_TYPE,
			'Accept': JSON_CONTENT_TYPE
		}
	});
}

const getFactors = cleanAlias => {
	$.get("/multiplications/random", data => {
		attempt.val("");
		resultMessage.val("");

		if (cleanAlias) {
			userAlias.val("");
		}
		
		factorB.empty().append(data.factorB);
		factorA.empty().append(data.factorA);
	})
}

const postAttemp = data => {
	$.post("/results", JSON.stringify(data), response => {
		if (response.attempt.correct) {
			resultMessage.empty().append("The result is correct! Congratulations!");
		} else {
			resultMessage.empty().append("Oops that's not correct! But keep trying!");
		}
	});
}

const updateStats = alias => {
	$.get(`/results?alias=${alias}`, data => {
		statsBody.empty();

		data.forEach(row => {
			statsBody.append(`<tr>
													<td>${row.id}</td>
													<td>${row.multiplication.factorA} x ${row.multiplication.factorB} </td>
													<td>${row.product}</td><td>${row.correct}</td>
												</tr>`);
		});
	});
}