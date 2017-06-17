<#import "../header.ftl" as h>
<@h.header>

</@h.header>

<br>
<div id="header">
    <h2>
        <span>Create a new Teacher</span>
    </h2>
</div>
<div class="modal-body">
    <div class="form-group">
        <label class="form-control-label" for="firstName">First Name</label>
        <input type="text" class="form-control" id="firstName" name="firstName"
               minlength=3 maxlength=50 required>
        <#--<div *ngIf="firstName.dirty && firstName.invalid">
            <small class="form-text text-danger"
                   *ngIf="firstName.errors.required" jhiTranslate="entity.validation.required">
                This field is required.
            </small>
            <small class="form-text text-danger"
                   *ngIf="firstName.errors.minlength" jhiTranslate="entity.validation.minlengthL">
                This field cannot be less than 3 characters.
            </small>
            <small class="form-text text-danger"
                   *ngIf="firstName.errors.maxlength" jhiTranslate="entity.validation.maxlengthL">
                This field cannot be longer than 50 characters.
            </small>
        </div>-->
    </div>
    <div class="form-group">
        <label class="form-control-label">Last Name</label>
        <input type="text" class="form-control" name="lastName"
               minlength=3 maxlength=50 required>
        <#--<div *ngIf="lastName.dirty && lastName.invalid">
            <small class="form-text text-danger"
                   *ngIf="lastName.errors.required" jhiTranslate="entity.validation.required">
                This field is required.
            </small>
            <small class="form-text text-danger"
                   *ngIf="lastName.errors.maxlength" jhiTranslate="entity.validation.maxlengthL">
                This field cannot be longer than 50 characters.
            </small>
            <small class="form-text text-danger"
                   *ngIf="lastName.errors.minlength" jhiTranslate="entity.validation.minlengthL">
                This field cannot be less than 3 characters.
            </small>
        </div>-->
    </div>
    <div class="form-group">
        <label class="form-control-label" for="email">Email</label>
        <input type="email" class="form-control" id="email" name="email"
               required minlength="7" maxlength="100">
        <#--<div *ngIf="email.dirty && email.invalid">
            <small class="form-text text-danger"
                   *ngIf="email.errors.required" jhiTranslate="entity.validation.required">
                This field is required.
            </small>
            <small class="form-text text-danger"
                   *ngIf="email.errors.email" jhiTranslate="global.messages.validate.email.invalid">
                Your email is invalid.
            </small>
            <small class="form-text text-danger"
                   *ngIf="email.errors.minlength" jhiTranslate="entity.validation.minlengthE"
                   translate-value-min="7">
                This field cannot be less than 7 characters.
            </small>
            <small class="form-text text-danger"
                   *ngIf="email.errors.maxlength" jhiTranslate="entity.validation.maxlengthE"
                   translate-value-max="100">
                This field cannot be longer than 100 characters.
            </small>
        </div>-->
    </div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">
        <span class="fa fa-ban"></span>&nbsp;<span>Cancel</span>
    </button>
    <button type="submit" class="btn btn-primary">
        <span class="fa fa-save"></span>&nbsp;<span>Save</span>
    </button>
</div>

<script src="/scripts/teachermgmt.js"></script>


<@h.footer>

</@h.footer>
