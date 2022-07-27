package com.ttopgallery.ui.states

/** Different authentication ui status */
enum class AuthenticationUiStatus {
    /** Indicates that the app is currently doing nothing  */
    Idle,

    /** Indicates that the app is currently generating one-time-password.  */
    GeneratingOtp,

    /** Indicates that otp was successfully generated.   */
    OtpGeneratedSuccessfully,

    /** Indicates that the app is currently logging in a user.  */
    LoggingIn,

    /** Indicates successful login.  */
    LoginSuccessful,

    /** Indicates that the app is currently registering a user.  */
    Registering,

    /** Indicates successful user registration.  */
    RegistrationSuccessful,

    /** Indicates that the app is currently resetting a password.  */
    ResettingPassword,

    /** Indicates successful password reset.  */
    ResetPasswordSuccessful,

    /** Indicates that the app is currently changing a password.  */
    ChangingPassword,

    /** Indicates successful password change.  */
    ChangePasswordSuccessful,

    /** Indicates invalid input.  */
    InvalidInput,

    /** Indicates failure  */
    Failure
}