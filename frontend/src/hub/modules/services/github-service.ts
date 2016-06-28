import module = require('./module');
import angular = require("{angular}/angular");
import IRequestConfig = angular.IRequestConfig;
import IQResolveReject = angular.IQResolveReject; //

const GITHUB_API = 'https://api.github.com/';
const GITHUB_API_VERSION = 'application/vnd.github.v3+json';

interface SourceService {
    searchUserRepositories(user:string): ng.IPromise<any>;
}

enum GithubSort {
    STARS = <any> 'stars',
    FORKS = <any> 'forks',
    UPDATED = <any> 'updated'
}

enum GithubOrder {
    ASC = <any> 'asc',
    DESC = <any> 'desc'
}

export class GithubService implements SourceService {

    private GITHUB_TOKEN: string;

    static $inject = ['$q', '$httpParamSerializer'];
    constructor(private $q:ng.IQService, private $httpParamSerializer) {}

    public setUserToken (token: string): void {
       this.GITHUB_TOKEN = token;
    }

    public getUserToken (): string {
        return this.GITHUB_TOKEN;
    }

    public searchUserRepositories(user:string, per_page?:number, sort?:GithubSort, order?:GithubOrder):ng.IPromise<any> {
        return this.$q((resolve, reject) => {
            if (user) {
                var params = {
                    sort: sort,
                    order: order,
                    per_page: per_page || 50
                };
                if (this.GITHUB_TOKEN) {
                    params['access_token'] = this.GITHUB_TOKEN;
                }
                var url:string = GITHUB_API + 'users/' + user + '/repos?' + this.$httpParamSerializer(params);
                var xhr:XMLHttpRequest = this.createCORSRequest('GET', url);
                if (!xhr) {
                    console.info('CORS not supported');
                    resolve();
                }
                xhr.onload = function () {
                    resolve(JSON.parse(xhr.responseText));
                };
                xhr.onerror = function (error) {
                    reject(error);
                };
                xhr.send();
            } else {
                resolve();
            }
        });
    }

    private createCORSRequest = (method, url):XMLHttpRequest => {
        var xhr = new XMLHttpRequest();
        if ("withCredentials" in xhr) {
            xhr.open(method, url, true);
        } else if (typeof window['XDomainRequest'] != "undefined") {
            xhr = new window['XDomainRequest']();
            xhr.open(method, url);
        } else {
            xhr = null;
        }
        return xhr;
    };
}

angular
    .module(module.angularModules)
    .service('GithubService', GithubService);