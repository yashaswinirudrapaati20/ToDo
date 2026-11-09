
<%@ include file="common/header.jspf" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-5">

            <div class="card shadow">
                <div class="card-body p-4">

                    <h3 class="text-center mb-3">
                        Verify OTP
                    </h3>

                    <p class="text-muted text-center">
                        Enter the 6-digit OTP sent to your email.
                        The OTP is valid for 5 minutes.
                    </p>

                    <form action="${pageContext.request.contextPath}/verify-otp"
                          method="post">

                        <div class="mb-3">
                            <label class="form-label">Email Address</label>

                            <input type="email"
                                   name="email"
                                   class="form-control"
                                   value="${email}"
                                   required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Enter OTP</label>

                            <input type="text"
                                   name="otp"
                                   class="form-control"
                                   placeholder="Enter 6-digit OTP"
                                   pattern="[0-9]{6}"
                                   maxlength="6"
                                   inputmode="numeric"
                                   required>
                        </div>

                        <button type="submit"
                                class="btn btn-primary w-100">
                            Verify OTP
                        </button>

                    </form>

                    <c:if test="${not empty message}">
                        <div class="alert alert-info mt-3">
                            ${message}
                        </div>
                    </c:if>

                    <div class="text-center mt-3">
                        <a href="${pageContext.request.contextPath}/forgot-password">
                            Back
                        </a>
                    </div>

                </div>
            </div>

        </div>
    </div>
</div>

<%@ include file="common/footer.jspf" %>
