/**
 * Copyright 2015 Guillermo Bauzá (birelian) - birelianATgmailDOTcom
 *
 *
 * This file is part of angularContacts.
 *
 * angularContacts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * angularContacts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with angularContacts.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

 angular.module("app", ["ngResource"])

    // Contact REST service
    .factory("Contact", function($resource) {
        return $resource('/api/contacts/:id', { id: '@_id' });
    })

    .controller("controller", function($scope, Contact) {

        $scope.contactData = {};
        $scope.submitted = false;

        $scope.contacts = Contact.query();

        // User wants to create a new Contact
        $scope.showNewUserForm = function() {

            $scope.initializeNewContact();
            $scope.showContactForm = true;
        }

        // Initialize new contact
        $scope.initializeNewContact = function() {

            // Clean values
            $scope.contactData.id = "";
            $scope.contactData.name = "";
            $scope.contactData.surname = "";
            $scope.contactData.email = "";
            $scope.contactData.phone = "";

            // Clean UI
            $scope.contactForm.$setPristine();
            $scope.submitted = false;
        }

        // Cancel create / edit contact
        $scope.cancelEditContact = function() {
            $scope.showContactForm = false;
        }

        // Save contact
        $scope.saveContact = function() {

            $scope.submitted = true;

            // Valid?
            if ($scope.contactForm.$valid) {

                var contact = new Contact();

                contact.id = $scope.contactData.id;
                contact.name = $scope.contactData.name;
                contact.surname = $scope.contactData.surname;
                contact.email = $scope.contactData.email;
                contact.phone = $scope.contactData.phone;

                contact.$save(function(data){

                    if (data.error_message) {
                        // Do something here...
                    } else {
                        $scope.contactData = {}
                        $scope.showContactForm = false;
                        $scope.contacts = Contact.query();
                    }
                });
            }
        }

        // Edit a contact
        $scope.editContact = function(contact) {
            $scope.contactData = contact;
            $scope.showContactForm = true;
        }

        // Delete contact
        $scope.deleteContact = function(contact) {

            console.log('Deleting');

            // Delete
            contact.$delete({id : contact.id},  function(data){

                if (data.error_message) {
                    console.log("Error: " + data.error_message);
                } else {
                    console.log('ok');
                    $scope.contactData = {}
                    $scope.showContactForm = false;
                    $scope.contacts = Contact.query();
                }
            });
        }


    });
