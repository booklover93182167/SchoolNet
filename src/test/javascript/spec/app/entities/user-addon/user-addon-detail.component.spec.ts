import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { SchoolNetTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { UserAddonDetailComponent } from '../../../../../../main/webapp/app/entities/user-addon/user-addon-detail.component';
import { UserAddonService } from '../../../../../../main/webapp/app/entities/user-addon/user-addon.service';
import { UserAddon } from '../../../../../../main/webapp/app/entities/user-addon/user-addon.model';

describe('Component Tests', () => {

    describe('UserAddon Management Detail Component', () => {
        let comp: UserAddonDetailComponent;
        let fixture: ComponentFixture<UserAddonDetailComponent>;
        let service: UserAddonService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SchoolNetTestModule],
                declarations: [UserAddonDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    UserAddonService,
                    EventManager
                ]
            }).overrideComponent(UserAddonDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserAddonDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserAddonService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new UserAddon(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.userAddon).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
